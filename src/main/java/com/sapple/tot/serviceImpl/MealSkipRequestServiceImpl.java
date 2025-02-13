package com.sapple.tot.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapple.tot.entities.Hostel;
import com.sapple.tot.entities.MealSkipRequest;
import com.sapple.tot.entities.MealType;
import com.sapple.tot.entities.Tenant;
import com.sapple.tot.entities.User;
import com.sapple.tot.exceptions.CustomListException;
import com.sapple.tot.exceptions.CustomObjectException;
import com.sapple.tot.helperDto.MealSkipCommonDataDto;
import com.sapple.tot.helperDto.MealSkipCreationDto;
import com.sapple.tot.helperDto.TenantCommonInfoDto;
import com.sapple.tot.repositories.MealSkipRequestRepository;
import com.sapple.tot.repositories.TenantRepository;
import com.sapple.tot.repositories.UserRepository;
import com.sapple.tot.requestDto.MealSkipGetDataRequest;
import com.sapple.tot.requestDto.MealSkipRequestCreationRequest;
import com.sapple.tot.responseDto.MealSkipForOwnerResponse;
import com.sapple.tot.responseDto.MealSkipForTenantResponse;
import com.sapple.tot.service.MealSkipRequestService;

@Service
public class MealSkipRequestServiceImpl implements MealSkipRequestService {
	@Autowired
	UserRepository userRepo;

	@Autowired
	TenantRepository tenantRepo;

	@Autowired
	MealSkipRequestRepository mealSkipReqRepo;

	@Transactional
	@Override
	public void createMealSkipRequest(MealSkipRequestCreationRequest request, Long userId) {
		Tenant tenant = tenantRepo.getTenantByUserId(userId);
		if (tenant == null) {
			throw new CustomObjectException("20:user is not a tenant");
		} else {
			for (MealSkipCreationDto temp : request.getList()) {
				mealSkipReqRepo.deleteForSpecificDateAndTenant(tenant.getId(), temp.getDate());
				for (Long mealTypeId : temp.getMealTypeList()) {
					MealSkipRequest entity = new MealSkipRequest();
					entity.setDate(temp.getDate());

					Hostel hostel = new Hostel();
					hostel.setId(request.getHostelId());
					entity.setHostel(hostel);

					MealType mealType = new MealType();
					mealType.setId(mealTypeId);
					entity.setMealType(mealType);

					entity.setTenant(tenant);

					mealSkipReqRepo.save(entity);
				}
			}
		}
	}

	@Transactional
	@Override
	public void deleteRequest(Long id, Long userId) {
		Tenant tenant = tenantRepo.getTenantByUserId(userId);
		if (tenant == null) {
			throw new CustomObjectException("20:user is not a tenant");
		} else {
			mealSkipReqRepo.deleteById(id);
		}
	}

	@Transactional
	@Override
	public List<MealSkipForTenantResponse> getMealSkipDataForTenant(Date date, Long userId) {
		Tenant tenant = tenantRepo.getTenantByUserId(userId);
		if (tenant == null) {
			throw new CustomListException("20:user is not a tenant");
		} else {
			List<MealSkipForTenantResponse> returnList = new ArrayList<MealSkipForTenantResponse>();

			List<MealSkipRequest> tempList = null;
			if (date == null) {
				tempList = mealSkipReqRepo.getMealSkipDataForTenantForTwoDays(tenant.getId());
			} else {
				tempList = mealSkipReqRepo.getMealSkipDataForTenantForSpecificDay(tenant.getId(), date);
			}
			Date tempMealSkipDate = null;
			MealSkipForTenantResponse returnObject = null;
			for (MealSkipRequest temp : tempList) {
				if (tempMealSkipDate == null || tempMealSkipDate.compareTo(temp.getDate()) != 0) {
					returnObject = new MealSkipForTenantResponse();
					returnObject.setDate(temp.getDate());
					returnObject.setMealTypeList(new ArrayList<MealSkipCommonDataDto>());

					returnList.add(returnObject);

					tempMealSkipDate = temp.getDate();
				}
				MealSkipCommonDataDto mealSkipTempData = new MealSkipCommonDataDto();
				mealSkipTempData.setMealSkipId(temp.getId());
				mealSkipTempData.setMealSkipTypeId(temp.getMealType().getId());
				returnObject.getMealTypeList().add(mealSkipTempData);
			}
			return returnList;
		}
	}

	@Transactional
	@Override
	public MealSkipForOwnerResponse getMealSkipDataForOwner(MealSkipGetDataRequest request, Long userId) {
		User user = userRepo.getOne(userId);
		if (user == null) {
			throw new CustomObjectException("12:user not found");
		} else {
			if (user.getOwner() == null) {
				throw new CustomObjectException("17:user is not hostel owner");
			} else {
				MealSkipForOwnerResponse returnObject = new MealSkipForOwnerResponse();

				List<MealSkipRequest> tempList;
				if (request.getDate() == null) {
					tempList = mealSkipReqRepo.getMealSkipDataForOwnerForCurrentDay(request.getHostelId());
				} else {
					tempList = mealSkipReqRepo.getMealSkipDataForOwnerForSpecificDay(request.getHostelId(),
							request.getDate());
				}

				Date tempMealSkipDate = null;
				for (MealSkipRequest temp : tempList) {
					if (tempMealSkipDate == null || tempMealSkipDate.compareTo(temp.getDate()) != 0) {
						tempMealSkipDate = temp.getDate();
						returnObject.setDate(temp.getDate());
						returnObject.setBreakfastSkipList(new ArrayList<TenantCommonInfoDto>());
						returnObject.setLunchSkipList(new ArrayList<TenantCommonInfoDto>());
						returnObject.setDinnerSkipList(new ArrayList<TenantCommonInfoDto>());
					}
					TenantCommonInfoDto tempDto = new TenantCommonInfoDto();
					tempDto.setTenantId(temp.getTenant().getId());
					String tenantName = user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName();
					tempDto.setTenantName(tenantName);
					if (temp.getMealType().getId() == 1) {
						returnObject.getBreakfastSkipList().add(tempDto);
					} else if (temp.getMealType().getId() == 2) {
						returnObject.getLunchSkipList().add(tempDto);
					} else {
						returnObject.getDinnerSkipList().add(tempDto);
					}
					
					mealSkipReqRepo.updateSeenStatus(temp.getId());
				}
				return returnObject;
			}
		}
	}
}
