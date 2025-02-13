package com.sapple.tot.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapple.tot.entities.Hostel;
import com.sapple.tot.entities.Owner;
import com.sapple.tot.entities.Role;
import com.sapple.tot.entities.Tenant;
import com.sapple.tot.entities.User;
import com.sapple.tot.exceptions.CustomObjectException;
import com.sapple.tot.repositories.OwnerRepository;
import com.sapple.tot.repositories.RoleRepository;
import com.sapple.tot.repositories.TenantRepository;
import com.sapple.tot.repositories.UserRepository;
import com.sapple.tot.requestDto.OwnerDataRequest;
import com.sapple.tot.requestDto.UserRegistrationRequest;
import com.sapple.tot.responseDto.OwnerInfoResponse;
import com.sapple.tot.service.HostelOwnerService;

@Service
public class HostelOwnerServiceImpl implements HostelOwnerService {
	@Autowired
	private OwnerRepository ownerRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private TenantRepository tenantRepo;

	@Transactional
	@Override
	public void saveOwnerInfo(OwnerDataRequest request, Long userId) {
		User user = userRepo.getOne(userId);
		if (user != null) {
			Owner owner = user.getOwner();
			if (request.getDataSetIdentifier() == 1) {
				user.setFirstName(request.getFirstName());
				user.setMiddleName(request.getMiddleName());
				user.setLastName(request.getLastName());
				user.setDateUpdated(Calendar.getInstance().getTime());
				userRepo.save(user);

				owner.setAddressLine1(request.getAddressLine1());
				owner.setAddressLine2(request.getAddressLine2());
				owner.setAddressLine3(request.getAddressLine3());
				owner.setDateUpdated(Calendar.getInstance().getTime());
				ownerRepo.save(owner);
			} else {
				throw new CustomObjectException("13:invalid data to be saved");
			}
		} else {
			throw new CustomObjectException("12:user not found");
		}
	}
	
	@Transactional
	@Override
	public OwnerInfoResponse getOwnerInfo(Integer dataSetIdentifier, Long userId) {
		User user = userRepo.getOne(userId);
		if (user != null) {
			OwnerInfoResponse response = new OwnerInfoResponse();
			response.setDataSetIdentifier(dataSetIdentifier);
			if (dataSetIdentifier == 1) {
				response.setFirstName(user.getFirstName());
				response.setMiddleName(user.getMiddleName());
				response.setLastName(user.getLastName());
				response.setAddressLine1(user.getOwner().getAddressLine1());
				response.setAddressLine2(user.getOwner().getAddressLine2());
				response.setAddressLine3(user.getOwner().getAddressLine3());
				return response;
			} else {
				throw new CustomObjectException("18:data requested is invalid");
			}
		} else {
			throw new CustomObjectException("12:user not found");
		}
	}

	@Transactional
	@Override
	public void registerUser(UserRegistrationRequest request, Long userId) {
		User user = userRepo.findByContactNo(request.getContactNo());
		if (user == null) {
			if (request.getRoleId() == 1) {
				throw new CustomObjectException("15:owner can not enlist another owner");
			} else {
				User entity = new User();
				entity.setContactNo(request.getContactNo());

				List<Role> roles = new ArrayList<Role>();
				roles.add(roleRepo.getOne(request.getRoleId()));
				entity.setRoles(roles);

				User enlistingUser = new User();
				enlistingUser.setId(userId);
				entity.setEnlistedBy(enlistingUser);
				entity.setInsideHostel(0);

				userRepo.save(entity);
				
				if(request.getRoleId() == 2) {
					Tenant tenant = new Tenant();
					tenant.setUser(entity);
					
					Hostel hostel = new Hostel();
					hostel.setId(request.getHostelId());
					tenant.setHostel(hostel);
					
					tenantRepo.save(tenant);
				}
			}
		} else {
			if (user.getIsActive() == 1) {
				if (user.getRoles().get(0).getId() == 1) {
					throw new CustomObjectException("3:mobile number already registered for hostel owner");
				} else if (user.getRoles().get(0).getId() == 2) {
					throw new CustomObjectException("4:mobile number already registered for tenant");
				} else if (user.getRoles().get(0).getId() == 3) {
					throw new CustomObjectException("5:mobile number already registered for security guard");
				} else {
					throw new CustomObjectException("6:mobile number already registered for warden");
				}
			} else if (user.getEnlistedBy() != null) {
				throw new CustomObjectException("14:this number has already been enlisted by another owner");
			} else {
				throw new CustomObjectException("16:already enlisted this user, please ask the person to login");
			}
		}
	}
}
