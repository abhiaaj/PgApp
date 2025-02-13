package com.sapple.tot.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapple.tot.entities.Media;
import com.sapple.tot.entities.Tenant;
import com.sapple.tot.entities.User;
import com.sapple.tot.entities.VisitorRequest;
import com.sapple.tot.exceptions.CustomListException;
import com.sapple.tot.exceptions.CustomObjectException;
import com.sapple.tot.helperDto.VisitorRequestDataForOwnerDto;
import com.sapple.tot.repositories.TenantRepository;
import com.sapple.tot.repositories.UserRepository;
import com.sapple.tot.repositories.VisitorRequestsRepository;
import com.sapple.tot.requestDto.OtpConfirmRequest;
import com.sapple.tot.requestDto.VisitorRequestCreationRequest;
import com.sapple.tot.requestDto.VisitorRequestUpdateRequest;
import com.sapple.tot.responseDto.VisitorRequestsForTenantResponse;
import com.sapple.tot.responseDto.VisitiorOtpVerificationResponse;
import com.sapple.tot.responseDto.VisitorRequestsForOwnerResponse;
import com.sapple.tot.service.VisitorRequestsService;

@Service
public class VisitorRequestsServiceImpl implements VisitorRequestsService {
	@Autowired
	VisitorRequestsRepository visitorRequestsRepo;

	@Autowired
	TenantRepository tenantRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	private VisitorRequestsRepository visitorRequestRepo;

	@Transactional
	@Override
	public void createVisitorRequest(VisitorRequestCreationRequest request, Long userId) {
		Tenant tenant = tenantRepo.getTenantByUserId(userId);

		if (tenant == null) {
			throw new CustomObjectException("20:user is not a tenant");
		} else {
			VisitorRequest entity = new VisitorRequest();
			entity.setEntryTime(request.getEntryTime());
			entity.setExitTime(request.getExitTime());
			entity.setVisitorName(request.getName());
			entity.setMobileNo(request.getMobileNo());
			entity.setReasonOfVisit(request.getReasonOfVisit());
			entity.setOvernightStay(request.getOvernightStay());
			entity.setTenant(tenant);
			entity.setHostel(tenant.getHostel());
			entity.setHostelFood(request.getHostelFood());

			// for now the otp is hard-coded to 111111
			entity.setOtp(111111);
			entity.setOtpStatus(0);
			entity.setSmsStatus(0);
			
			Media media = new Media();
			// for now hard code the image data
			media.setId(1);
			entity.setMedia(media);

			entity.setIsActive(1);
			visitorRequestsRepo.save(entity);
		}
	}

	@Transactional
	@Override
	public List<VisitorRequestsForTenantResponse> getVisitorRequestsForTenant(Long userId) {
		Tenant tenant = tenantRepo.getTenantByUserId(userId);

		if (tenant == null) {
			throw new CustomListException("20:user is not a tenant");
		} else {
			List<VisitorRequest> tempList = visitorRequestsRepo.getVisitorRequestsForTenant(tenant.getId());
			List<VisitorRequestsForTenantResponse> returnList = new ArrayList<VisitorRequestsForTenantResponse>();
			for (VisitorRequest vr : tempList) {
				VisitorRequestsForTenantResponse returnObject = new VisitorRequestsForTenantResponse();
				returnObject.setId(vr.getId());
				returnObject.setEntryTime(vr.getEntryTime());
				returnObject.setExitTime(vr.getExitTime());
				returnObject.setName(vr.getVisitorName());
				returnObject.setMobileNo(vr.getMobileNo());
				returnObject.setReasonOfVisit(vr.getReasonOfVisit());
				returnObject.setOvernightStay(vr.getOvernightStay());
				if (vr.getOtpStatus() == 0) {
					returnObject.setOtpStatus("Pending");
				} else if (vr.getOtpStatus() == 1) {
					returnObject.setOtpStatus("Accepted");
				} else {
					returnObject.setOtpStatus("Rejected");
				}
				returnObject.setSmsStatus(vr.getSmsStatus());
				if (vr.getDateApproved() == null) {
					if (vr.getDateRejected() == null) {
						if (vr.getDatePutOnHold() == null) {
							returnObject.setRequestStatus("Pending");
						} else {
							returnObject.setRequestStatus("OnHold");
						}
					} else {
						returnObject.setRequestStatus("Rejected");
					}
				} else {
					returnObject.setRequestStatus("Accepted");
				}

				returnList.add(returnObject);
			}
			return returnList;
		}
	}

	@Transactional
	@Override
	public VisitorRequestsForOwnerResponse getVisitorRequestsForOwner(Long hostelId, Long userId) {
		try {
			VisitorRequestsForOwnerResponse returnObject = new VisitorRequestsForOwnerResponse();
			returnObject.setMeetingVisitorList(new ArrayList<VisitorRequestDataForOwnerDto>());
			returnObject.setPendingOvernightVisitorList(new ArrayList<VisitorRequestDataForOwnerDto>());
			returnObject.setApprovedOvernighVisitorList(new ArrayList<VisitorRequestDataForOwnerDto>());

			List<VisitorRequest> tempList = visitorRequestsRepo.getVisitorRequestsForOwner(hostelId);
			for (VisitorRequest vr : tempList) {
				VisitorRequestDataForOwnerDto tempObject = new VisitorRequestDataForOwnerDto();
				tempObject.setId(vr.getId());
				tempObject.setEntryTime(vr.getEntryTime());
				tempObject.setExitTime(vr.getExitTime());
				tempObject.setVisitorName(vr.getVisitorName());

				User user = userRepo.getOne(userId);
				tempObject.setTenantName(user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());

				tempObject.setMobileNo(vr.getMobileNo());
				tempObject.setReasonOfVisit(vr.getReasonOfVisit());
				tempObject.setOvernightStay(vr.getOvernightStay());
				if (vr.getOtpStatus() == 0) {
					tempObject.setOtpStatus("Pending");
				} else if (vr.getOtpStatus() == 1) {
					tempObject.setOtpStatus("Accepted");
				} else {
					tempObject.setOtpStatus("Rejected");
				}
				tempObject.setSmsStatus(vr.getSmsStatus());
				if (vr.getDateApproved() == null) {
					if (vr.getDateRejected() == null) {
						if (vr.getDatePutOnHold() == null) {
							tempObject.setRequestStatus("Pending");
						} else {
							tempObject.setRequestStatus("OnHold");
						}
					} else {
						tempObject.setRequestStatus("Rejected");
					}
				} else {
					tempObject.setRequestStatus("Approved");
				}
				if (vr.getOvernightStay() == 0) {
					returnObject.getMeetingVisitorList().add(tempObject);
				} else {
					if (vr.getDateApproved() == null && vr.getDateRejected() == null) {
						returnObject.getPendingOvernightVisitorList().add(tempObject);
					} else if (vr.getDateApproved() != null) {
						returnObject.getApprovedOvernighVisitorList().add(tempObject);
					}
				}
				visitorRequestsRepo.updateSeenStatus(vr.getId());
			}
			return returnObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@Override
	public void deleteRequest(Long id, Long userId) {
		Tenant tenant = tenantRepo.getTenantByUserId(userId);

		if (tenant == null) {
			throw new CustomObjectException("20:user is not a tenant");
		} else {
			visitorRequestsRepo.deleteById(id);
		}
	}

	@Transactional
	@Override
	public void updateVisitorRequestStatus(VisitorRequestUpdateRequest request, Long userId) {
		User user = userRepo.getOne(userId);

		if (user == null) {
			throw new CustomObjectException("12:user not found");
		} else {
			VisitorRequest vr = visitorRequestRepo.getOne(request.getVisitorRequestId());
			if (vr == null) {
				throw new CustomObjectException("26:visitor request not found");
			} else {
				if (request.getStatusId() == 1) {
					vr.setDateApproved(Calendar.getInstance().getTime());
				} else if (request.getStatusId() == 2) {
					vr.setDateRejected(Calendar.getInstance().getTime());
				} else {
					vr.setDatePutOnHold(Calendar.getInstance().getTime());
				}
				vr.setRequestStatusUpdated(1);
				visitorRequestRepo.save(vr);
			}
		}
	}

	@Transactional
	@Override
	public VisitiorOtpVerificationResponse verifyVisitorOtp(OtpConfirmRequest request, Long userId) {
		VisitorRequest vr = visitorRequestRepo.getRequestByContactNo(request.getContactNo());
		if (vr == null) {
			throw new CustomObjectException("27:no visitor request for this number");
		} else {
			if (vr.getOvernightStay() == 1) {
				if (vr.getRequestStatusUpdated() == 0) {
					throw new CustomObjectException("28:this request has not been viewed by owner yet");
				} else if (vr.getDateRejected() != null) {
					throw new CustomObjectException("29:this request has been rejected by owner");
				} else if (vr.getDatePutOnHold() != null) {
					throw new CustomObjectException("30:this request has been put on hold by owner");
				} else {
					return subVerifyVisitorOtp(request);
				}
			} else {
				return subVerifyVisitorOtp(request);
			}
		}
	}

	private VisitiorOtpVerificationResponse subVerifyVisitorOtp(OtpConfirmRequest request) {
		VisitorRequest tempVr = visitorRequestRepo.verifyVisitorOtp(request.getContactNo(), request.getOtp());
		if (tempVr != null) {
			VisitiorOtpVerificationResponse returnObject = new VisitiorOtpVerificationResponse();
			returnObject.setVisitorName(tempVr.getVisitorName());
			User user = tempVr.getTenant().getUser();
			String tenantName = "";
			if (user.getMiddleName() != null) {
				tenantName = user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName();
			} else {
				tenantName = user.getFirstName() + " " + user.getLastName();
			}
			returnObject.setTenantName(tenantName);
			returnObject.setTenantMobileNo(user.getContactNo());
			returnObject.setOvernightStay(tempVr.getOvernightStay());
			if (tempVr.getMedia() != null) {
				returnObject.setVisitorIdentityProofUrl(tempVr.getMedia().getUrl());
			} else {
				returnObject.setVisitorIdentityProofUrl(null);
			}

			return returnObject;
		} else {
			throw new CustomObjectException("31:otp does not match");
		}
	}
}
