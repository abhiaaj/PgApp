package com.sapple.tot.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapple.tot.entities.Facilities;
import com.sapple.tot.entities.FoodPreference;
import com.sapple.tot.entities.Institute;
import com.sapple.tot.entities.LocalGuardian;
import com.sapple.tot.entities.Parent;
import com.sapple.tot.entities.Tenant;
import com.sapple.tot.entities.TenantFacilities;
import com.sapple.tot.entities.TenantType;
import com.sapple.tot.entities.User;
import com.sapple.tot.entities.Workplace;
import com.sapple.tot.exceptions.CustomObjectException;
import com.sapple.tot.repositories.InstituteRepository;
import com.sapple.tot.repositories.LocalGuardianRepository;
import com.sapple.tot.repositories.ParentRepository;
import com.sapple.tot.repositories.TenantFacilitiesRepository;
import com.sapple.tot.repositories.TenantRepository;
import com.sapple.tot.repositories.UserRepository;
import com.sapple.tot.repositories.WorkplaceRepository;
import com.sapple.tot.requestDto.TenantDataRequest;
import com.sapple.tot.responseDto.TenantInfoResponse;
import com.sapple.tot.responseDto.ProfileStatusResponse;
import com.sapple.tot.service.TenantService;

@Service
public class TenantServiceImpl implements TenantService {
	@Autowired
	TenantRepository tenantRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	ParentRepository parentRepo;

	@Autowired
	LocalGuardianRepository lgRepo;

	@Autowired
	InstituteRepository instituteRepo;

	@Autowired
	WorkplaceRepository workplaceRepo;
	
	@Autowired
	TenantFacilitiesRepository tenantFacilitiesRepo;

	@Transactional
	@Override
	public ProfileStatusResponse getTenantProfileStatus(Long userId) {
		User user = userRepo.getOne(userId);
		if (user != null) {
			ProfileStatusResponse returnObject = new ProfileStatusResponse();

			if (user.getContactNo() == null || user.getFirstName() == null) {
				returnObject.setPersonalInfoFlag(0);
			} else {
				returnObject.setPersonalInfoFlag(1);
			}

			if (user.getTenant().getParent() == null) {
				returnObject.setParentInfoFlag(0);
			} else {
				returnObject.setParentInfoFlag(1);
			}

			if (user.getTenant().getLocalGuardian() == null) {
				returnObject.setLocalGuardianFlag(0);
			} else {
				returnObject.setLocalGuardianFlag(1);
			}

			// 1-student tenant
			// 2-working student
			if (user.getTenant().getTenantType() == null) {
				returnObject.setProfessionFlag(0);
			} else {
				returnObject.setProfessionFlag(1);
			}

			if (user.getTenant().getFoodPreference() == null) {
				returnObject.setMiscFlag(0);
			} else {
				returnObject.setMiscFlag(1);
			}

			// this is dummy for now
			returnObject.setOtherFlag(0);

			return returnObject;
		} else {
			throw new CustomObjectException("12:user not found");
		}
	}

	@Transactional
	@Override
	public void saveTenantInfo(TenantDataRequest request, Long userId) {
		User user = userRepo.getOne(userId);
		if (user != null) {
			Tenant tenant = user.getTenant();
			if (tenant == null) {
				throw new CustomObjectException("20:user is not a tenant");
			} else {
				if (request.getDataSetIdentifier() == 1) {
					user.setFirstName(request.getFirstName());
					user.setMiddleName(request.getMiddleName());
					user.setLastName(request.getLastName());
					userRepo.save(user);
				} else if (request.getDataSetIdentifier() == 2) {
					if (tenant.getParent() == null) {
						Parent parent = new Parent();
						parent.setFirstName(request.getParentFirstName());
						parent.setMiddleName(request.getParentMiddleName());
						parent.setLastName(request.getParentLastName());
						parent.setContactNo(request.getParentContactNo());
						parent.setAddressLine1(request.getParentAddressLine1());
						parent.setAddressLine2(request.getParentAddressLine2());
						parent.setAddressLine3(request.getParentAddressLine3());
						parentRepo.save(parent);
						
						tenant.setParent(parent);
						tenantRepo.save(tenant);
					} else {
						tenant.getParent().setFirstName(request.getParentFirstName());
						tenant.getParent().setMiddleName(request.getParentMiddleName());
						tenant.getParent().setLastName(request.getParentLastName());
						tenant.getParent().setContactNo(request.getParentContactNo());
						tenant.getParent().setAddressLine1(request.getParentAddressLine1());
						tenant.getParent().setAddressLine2(request.getParentAddressLine2());
						tenant.getParent().setAddressLine3(request.getParentAddressLine3());
						parentRepo.save(tenant.getParent());
					}
				} else if (request.getDataSetIdentifier() == 3) {
					if (tenant.getLocalGuardian() == null) {
						LocalGuardian lg = new LocalGuardian();
						lg.setFirstName(request.getLgFirstName());
						lg.setMiddleName(request.getLgMiddleName());
						lg.setLastName(request.getLgLastName());
						lg.setContactNo(request.getLgContactNo());
						lg.setAddressLine1(request.getLgAddressLine1());
						lg.setAddressLine2(request.getLgAddressLine2());
						lg.setAddressLine3(request.getLgAddressLine3());
						lgRepo.save(lg);
						
						tenant.setLocalGuardian(lg);
						tenantRepo.save(tenant);
					} else {
						tenant.getLocalGuardian().setFirstName(request.getLgFirstName());
						tenant.getLocalGuardian().setMiddleName(request.getLgMiddleName());
						tenant.getLocalGuardian().setLastName(request.getLgLastName());
						tenant.getLocalGuardian().setContactNo(request.getLgContactNo());
						tenant.getLocalGuardian().setAddressLine1(request.getLgAddressLine1());
						tenant.getLocalGuardian().setAddressLine2(request.getLgAddressLine2());
						tenant.getLocalGuardian().setAddressLine3(request.getLgAddressLine3());
						lgRepo.save(tenant.getLocalGuardian());
					}
				} else if (request.getDataSetIdentifier() == 4) {
					FoodPreference foodPreference = new FoodPreference();
					foodPreference.setId(request.getFoodPreference());
					tenant.setFoodPreference(foodPreference);

					tenant.setRoomNo(request.getRoomNo());
					tenantRepo.save(tenant);
					
					tenantFacilitiesRepo.deletePreviousEntries(tenant.getId());
					for(Long facilityId: request.getFacilities()) {
						TenantFacilities entity = new TenantFacilities();
						
						Facilities facility = new Facilities();
						facility.setId(facilityId);
						entity.setFacility(facility);
						
						entity.setTenant(tenant);
						tenantFacilitiesRepo.save(entity);
					}
				} else if (request.getDataSetIdentifier() == 5) {
					if (request.getTenantType() == 1) {
						if (tenant.getInstitute() == null) {
							Institute institute = new Institute();
							institute.setName(request.getInstituteName());
							institute.setAddressLine1(request.getInstituteAddressLine1());
							institute.setAddressLine2(request.getInstituteAddressLine2());
							institute.setAddressLine3(request.getInstituteAddressLine3());
							instituteRepo.save(institute);
							
							tenant.setInstitute(institute);
							TenantType tenantType = new TenantType();
							tenantType.setId(request.getTenantType());
							tenant.setTenantType(tenantType);
							tenantRepo.save(tenant);
						} else {
							tenant.getInstitute().setName(request.getInstituteName());
							tenant.getInstitute().setAddressLine1(request.getInstituteAddressLine1());
							tenant.getInstitute().setAddressLine2(request.getInstituteAddressLine2());
							tenant.getInstitute().setAddressLine3(request.getInstituteAddressLine3());
							instituteRepo.save(tenant.getInstitute());
						}
					} else {
						if (tenant.getWorkplace() == null) {
							Workplace workplace = new Workplace();
							workplace.setName(request.getInstituteName());
							workplace.setAddressLine1(request.getInstituteAddressLine1());
							workplace.setAddressLine2(request.getInstituteAddressLine2());
							workplace.setAddressLine3(request.getInstituteAddressLine3());
							workplaceRepo.save(workplace);
							
							tenant.setWorkplace(workplace);
							TenantType tenantType = new TenantType();
							tenantType.setId(request.getTenantType());
							tenant.setTenantType(tenantType);
							tenantRepo.save(tenant);
						} else {
							tenant.getWorkplace().setName(request.getInstituteName());
							tenant.getWorkplace().setAddressLine1(request.getInstituteAddressLine1());
							tenant.getWorkplace().setAddressLine2(request.getInstituteAddressLine2());
							tenant.getWorkplace().setAddressLine3(request.getInstituteAddressLine3());
							workplaceRepo.save(tenant.getWorkplace());
						}
					}
				} else {
					throw new CustomObjectException("13:invalid data to be saved");
				}
			}
		} else {
			throw new CustomObjectException("12:user not found");
		}
	}

	@Transactional
	@Override
	public TenantInfoResponse getTenantInfo(Integer dataSetIdentifier, Long userId) {
		User user = userRepo.getOne(userId);
		if (user != null) {
			if (user.getTenant() == null) {
				throw new CustomObjectException("20:user is not a tenant");
			} else {
				TenantInfoResponse response = new TenantInfoResponse();
				response.setDataSetIdentifier(dataSetIdentifier);
				if (dataSetIdentifier == 1) {
					response.setFirstName(user.getFirstName());
					response.setMiddleName(user.getMiddleName());
					response.setLastName(user.getLastName());
				} else if (dataSetIdentifier == 2) {
					if (user.getTenant().getParent() == null) {
						throw new CustomObjectException("21:tenant does not have parent info");
					} else {
						Parent parent = user.getTenant().getParent();
						response.setParentFirstName(parent.getFirstName());
						response.setParentMiddleName(parent.getMiddleName());
						response.setParentLastName(parent.getLastName());
						response.setParentContactNo(parent.getContactNo());
						response.setParentAddressLine1(parent.getAddressLine1());
						response.setParentAddressLine2(parent.getAddressLine2());
						response.setParentAddressLine3(parent.getAddressLine3());
					}
				} else if (dataSetIdentifier == 3) {
					if (user.getTenant().getLocalGuardian() == null) {
						throw new CustomObjectException("22:tenant does not have local guardian info");
					} else {
						LocalGuardian lg = user.getTenant().getLocalGuardian();
						response.setLgFirstName(lg.getFirstName());
						response.setLgMiddleName(lg.getMiddleName());
						response.setLgLastName(lg.getLastName());
						response.setLgContactNo(lg.getContactNo());
						response.setLgAddressLine1(lg.getAddressLine1());
						response.setLgAddressLine2(lg.getAddressLine2());
						response.setLgAddressLine3(lg.getAddressLine3());
					}
				} else if (dataSetIdentifier == 4) {
					response.setFoodPreference(user.getTenant().getFoodPreference().getId());
					response.setRoomNo(user.getTenant().getRoomNo());
					
					response.setFacilities(tenantFacilitiesRepo.getTenantFacilities(user.getTenant().getId()));
				} else if (dataSetIdentifier == 5) {
					if (user.getTenant().getTenantType().getId() == 1) {
						if (user.getTenant().getInstitute() == null) {
							throw new CustomObjectException("23:tenant does not have institute info");
						} else {
							response.setInstituteName(user.getTenant().getInstitute().getName());
						}
					} else {
						if (user.getTenant().getWorkplace() == null) {
							throw new CustomObjectException("24:tenant does not have workplace info");
						} else {

						}
					}
				} else {
					throw new CustomObjectException("18:data requested is invalid");
				}
				return response;
			}
		} else {
			throw new CustomObjectException("12:user not found");
		}
	}
}
