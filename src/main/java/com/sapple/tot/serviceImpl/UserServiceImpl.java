package com.sapple.tot.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sapple.tot.entities.Owner;
import com.sapple.tot.entities.Role;
import com.sapple.tot.entities.Tenant;
import com.sapple.tot.entities.User;
import com.sapple.tot.exceptions.CustomObjectException;
import com.sapple.tot.repositories.HostelFacilitiesRepository;
import com.sapple.tot.repositories.HostelRepository;
import com.sapple.tot.repositories.MealSkipRequestRepository;
import com.sapple.tot.repositories.OwnerRepository;
import com.sapple.tot.repositories.RoleRepository;
import com.sapple.tot.repositories.TenantRepository;
import com.sapple.tot.repositories.UserRepository;
import com.sapple.tot.repositories.VisitorRequestsRepository;
import com.sapple.tot.requestDto.OtpConfirmRequest;
import com.sapple.tot.requestDto.UserRegistrationRequest;
import com.sapple.tot.responseDto.ProfileStatusResponse;
import com.sapple.tot.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	TenantRepository tenantRepo;

	@Autowired
	OwnerRepository ownerRepo;

	@Autowired
	HostelRepository hostelRepo;

	@Autowired
	MealSkipRequestRepository mealSkipReqRepo;

	@Autowired
	VisitorRequestsRepository visitorReqRepo;

	@Autowired
	HostelFacilitiesRepository hostelFacilitiesRepo;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String contactNo) throws UsernameNotFoundException {
		// get the user entity
		User user = userRepo.findByContactNo(contactNo);
		if (user == null) {
			throw new CustomObjectException("12:user not found");
		} else {
			// check user email,password and role credentials is correct or not
			return new org.springframework.security.core.userdetails.User(user.getContactNo(), user.getPassword(),
					getAuthority(user));
		}
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		// check existing user has valid role or not
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
	}

	@Transactional
	@Override
	public Long createUser(UserRegistrationRequest request) {
		User user = userRepo.findByContactNo(request.getContactNo());

		if (user == null) {
			if (request.getRoleId() == 1) {
				// Generate otp when going production, for now use hard-coded
				String otp = "111111";
				User entity = new User();
				entity.setContactNo(request.getContactNo());
				entity.setPassword(otp);

				ArrayList<Role> roleList = new ArrayList<Role>();
				roleList.add(roleRepo.getOne(request.getRoleId()));
				entity.setRoles(roleList);

				entity.setRegistrationOtp(otp);
				entity.setRegistrationOtpExpiry(5); // 5 minutes of expiration time for otp
				entity.setDateUpdated(Calendar.getInstance().getTime());
				entity.setInsideHostel(0);
				userRepo.save(entity);

				Owner owner = new Owner();
				User tempUser = new User();
				tempUser.setId(entity.getId());
				owner.setDateUpdated(Calendar.getInstance().getTime());
				owner.setUser(tempUser);
				ownerRepo.save(owner);

				return entity.getId();
			} else {
				throw new CustomObjectException("26:only hostel owner can be registered without enlistment");
			}
		} else {
			if (user.getRoles().get(0).getId() == 1) {
				throw new CustomObjectException("3:mobile number already registered for hostel owner");
			} else if (user.getRoles().get(0).getId() == 2) {
				throw new CustomObjectException("4:mobile number already registered for tenant");
			} else if (user.getRoles().get(0).getId() == 3) {
				throw new CustomObjectException("5:mobile number already registered for security guard");
			} else {
				throw new CustomObjectException("6:mobile number already registered for warden");
			}
		}
	}

	@Transactional
	@Override
	public void confirmOtp(OtpConfirmRequest request) {
		User user = userRepo.findByContactNo(request.getContactNo());
		if (user != null) {
			if (user.getRegistrationOtpExpiry() == 0) {
				throw new CustomObjectException("9:registration already done");
			} else {
				Calendar cal = Calendar.getInstance();
				cal.setTime(user.getDateUpdated());
				cal.add(Calendar.MINUTE, user.getRegistrationOtpExpiry());

				if (cal.getTimeInMillis() < System.currentTimeMillis()) {
					throw new CustomObjectException("10:otp expired");
				} else {
					if (user.getRegistrationOtp().equalsIgnoreCase(request.getOtp())) {
						user.setRegistrationOtp(null);
						user.setRegistrationOtpExpiry(0);
						user.setIsActive(1);
						user.setDateUpdated(Calendar.getInstance().getTime());
						userRepo.save(user);
					} else {
						throw new CustomObjectException("11:otp does not match");
					}
				}
			}
		} else {
			throw new CustomObjectException("12:user not found");
		}
	}

	@Transactional
	@Override
	public void sendOtp(String contactNo) {
		User user = userRepo.findByContactNo(contactNo);
		if (user != null) {
			// Generate otp when going production, for now use hard-coded
			String otp = "111111";
			user.setRegistrationOtp(otp);
			user.setPassword(otp);
			user.setRegistrationOtpExpiry(5);// 5 minutes of expiration time for otp
			user.setDateUpdated(Calendar.getInstance().getTime());
			userRepo.save(user);
		} else {
			throw new CustomObjectException("12:user not found");
		}
	}

	@Transactional
	@Override
	public ProfileStatusResponse getProfileStatus(Long hostelId, Long userId) {
		User user = userRepo.getOne(userId);
		if (user != null) {
			if (user.getRoles().get(0).getId() == 1l) {
				if (user.getOwner() == null) {
					throw new CustomObjectException("17:user is not hostel owner");
				} else {
					ProfileStatusResponse returnObject = new ProfileStatusResponse();

					if (user.getContactNo() == null || user.getFirstName() == null
							|| user.getOwner().getAddressLine1() == null) {
						returnObject.setPersonalInfoFlag(0);
					} else {
						returnObject.setPersonalInfoFlag(1);
					}

					// this is dummy for now
					returnObject.setOtherFlag(0);

					returnObject.setHostelId(hostelId);
					returnObject.setHostelName(hostelRepo.getOne(hostelId).getName());
					returnObject.setMealRequests(mealSkipReqRepo.getUnseenRequests());
					returnObject.setMeetingVisitorRequests(visitorReqRepo.getUnseenMeetingVisitorReq());
					returnObject.setOvernightVisitorRequests(visitorReqRepo.getUnseenOvernightVisitorReq());

					return returnObject;
				}
			} else if (user.getRoles().get(0).getId() == 2l) {
				if (user.getTenant() == null) {
					throw new CustomObjectException("17:user is not tenant");
				} else {
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

					hostelId = user.getTenant().getHostel().getId();
					returnObject.setHostelId(hostelId);
					returnObject.setHostelName(hostelRepo.getOne(hostelId).getName());
					returnObject.setAvailableFacilityList(hostelFacilitiesRepo.getFacilitiesIds(hostelId));
					returnObject.setVisitorRequestUpdated(visitorReqRepo.getVisitorRequestStatusUpdated());
					visitorReqRepo.requestStatusFetchUpdated();

					return returnObject;
				}
			} else {
				return null;
			}
		} else {
			throw new CustomObjectException("12:user not found");
		}
	}
}
