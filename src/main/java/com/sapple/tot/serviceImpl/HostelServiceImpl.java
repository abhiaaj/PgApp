package com.sapple.tot.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapple.tot.entities.Facilities;
import com.sapple.tot.entities.FoodOptions;
import com.sapple.tot.entities.Hostel;
import com.sapple.tot.entities.HostelFacilities;
import com.sapple.tot.entities.HostelRoomCapacity;
import com.sapple.tot.entities.HostelVegDays;
import com.sapple.tot.entities.Owner;
import com.sapple.tot.entities.User;
import com.sapple.tot.exceptions.CustomListException;
import com.sapple.tot.exceptions.CustomObjectException;
import com.sapple.tot.helperDto.FacilitiesDto;
import com.sapple.tot.helperDto.HostelRoomCapacityDto;
import com.sapple.tot.repositories.HostelFacilitiesRepository;
import com.sapple.tot.repositories.HostelRepository;
import com.sapple.tot.repositories.HostelRoomCapacityRepository;
import com.sapple.tot.repositories.HostelVegDaysRepository;
import com.sapple.tot.repositories.OwnerRepository;
import com.sapple.tot.repositories.UserRepository;
import com.sapple.tot.requestDto.RegisterHostelRequest;
import com.sapple.tot.responseDto.HostelDataResponse;
import com.sapple.tot.responseDto.HostelListResponse;
import com.sapple.tot.service.HostelService;

@Service
public class HostelServiceImpl implements HostelService {
	@Autowired
	UserRepository userRepo;

	@Autowired
	HostelRepository hostelRepo;

	@Autowired
	HostelRoomCapacityRepository hostelRoomCapacityRepo;

	@Autowired
	OwnerRepository ownerRepo;

	@Autowired
	HostelVegDaysRepository hostelVegDaysRepo;

	@Autowired
	HostelFacilitiesRepository hostelFacilitiesRepo;

	@Transactional
	@Override
	public Long createHostel(RegisterHostelRequest request, Long userId) {
		User user = userRepo.getOne(userId);
		if (user.getRoles().get(0).getId() != 1l) {
			throw new CustomObjectException("17:user is not hostel owner");
		} else {
			Hostel hostel = new Hostel();
			hostel.setId(request.getId());
			hostel.setName(request.getName());
			hostel.setAddressLine1(request.getAddressLine1());
			hostel.setAddressLine2(request.getAddressLine2());
			hostel.setAddressLine3(request.getAddressLine3());
			hostel.setLandmark(request.getLandmark());
			hostel.setNumberOfBeds(request.getNumberOfBeds());
			hostel.setNumberOfRooms(request.getNumberOfRooms());
			hostel.setEntryTime(request.getEntryTime());
			hostel.setExitTime(request.getExitTime());
			hostel.setIsActive(1);

			Owner owner = new Owner();
			Long ownerId = ownerRepo.getOwnerByUserId(userId);
			owner.setId(ownerId);
			hostel.setOwner(owner);

			FoodOptions foodOption = new FoodOptions();
			foodOption.setId(request.getFoodOptionId());
			hostel.setFoodOption(foodOption);

			hostelRepo.save(hostel);

			if (request.getHostelRoomCapacity() != null) {
				hostelRoomCapacityRepo.deletePreviousEntriesForHostel(hostel.getId());
				for (HostelRoomCapacityDto temp : request.getHostelRoomCapacity()) {
					HostelRoomCapacity entity = new HostelRoomCapacity();
					entity.setNoOfRooms(temp.getNoOfRooms());
					entity.setSeater(temp.getSeater());
					entity.setFeePerMonth(temp.getFeePerMonth());
					entity.setSecurityDeposit(temp.getSecurityDeposit());

					Hostel tempHostel = new Hostel();
					tempHostel.setId(hostel.getId());
					entity.setHostel(tempHostel);
					hostelRoomCapacityRepo.save(entity);
				}
			}

			if (request.getVegFoodDays() != null) {
				hostelVegDaysRepo.deletePreviousEntriesForHostel(hostel.getId());
				for (Integer temp : request.getVegFoodDays()) {
					HostelVegDays entity = new HostelVegDays();
					entity.setDayId(temp);

					Hostel tempHostel = new Hostel();
					tempHostel.setId(hostel.getId());
					entity.setHostel(tempHostel);
					hostelVegDaysRepo.save(entity);
				}
			}

			if (request.getHostelFacilities() != null) {
				hostelFacilitiesRepo.deletePreviousEntriesForHostel(hostel.getId());
				for (FacilitiesDto facility : request.getHostelFacilities()) {
					HostelFacilities entity = new HostelFacilities();
					entity.setFee(facility.getFacilityFee());

					Hostel tempHostel = new Hostel();
					tempHostel.setId(hostel.getId());
					entity.setHostel(tempHostel);

					Facilities tempFacility = new Facilities();
					tempFacility.setId(facility.getFacilityId());
					entity.setFacility(tempFacility);
					hostelFacilitiesRepo.save(entity);
				}
			}

			HostelFacilities entity = new HostelFacilities();
			entity.setFee(request.getMessFee());

			Hostel tempHostel = new Hostel();
			tempHostel.setId(hostel.getId());
			entity.setHostel(tempHostel);

			Facilities tempFacility = new Facilities();
			tempFacility.setId(1); // mess facility is currently mandatory
			entity.setFacility(tempFacility);
			hostelFacilitiesRepo.save(entity);

			return hostel.getId();
		}
	}

	@Transactional
	@Override
	public HostelDataResponse getHostelData(Long hostelId) {
		Hostel hostel = hostelRepo.getOne(hostelId);
		if (hostel == null) {
			throw new CustomObjectException("19:hostel not found");
		} else {
			try {
				HostelDataResponse returnObject = new HostelDataResponse();
				returnObject.setName(hostel.getName());
				returnObject.setAddressLine1(hostel.getAddressLine1());
				returnObject.setAddressLine2(hostel.getAddressLine2());
				returnObject.setAddressLine3(hostel.getAddressLine3());
				returnObject.setLandmark(hostel.getLandmark());
				returnObject.setNumberOfBeds(hostel.getNumberOfBeds());
				returnObject.setNumberOfRooms(hostel.getNumberOfRooms());
				returnObject.setFoodOptionId(hostel.getFoodOption().getId());
				returnObject.setEntryTime(hostel.getEntryTime());
				returnObject.setExitTime(hostel.getExitTime());

				List<HostelRoomCapacity> hostelRoomCapacityList = hostelRoomCapacityRepo
						.getHostelRoomCapacityList(hostelId);
				if (hostelRoomCapacityList.size() > 0) {
					List<HostelRoomCapacityDto> hostelRoomCapacityReturnList = new ArrayList<HostelRoomCapacityDto>();
					for (HostelRoomCapacity hostelRoomCapacity : hostelRoomCapacityList) {
						HostelRoomCapacityDto temp = new HostelRoomCapacityDto();
						temp.setId(hostelRoomCapacity.getId());
						temp.setNoOfRooms(hostelRoomCapacity.getNoOfRooms());
						temp.setSeater(hostelRoomCapacity.getSeater());
						temp.setFeePerMonth(hostelRoomCapacity.getFeePerMonth());
						temp.setSecurityDeposit(hostelRoomCapacity.getSecurityDeposit());
						hostelRoomCapacityReturnList.add(temp);
					}
					returnObject.setHostelRoomCapacity(hostelRoomCapacityReturnList);
				} else {
					returnObject.setHostelRoomCapacity(null);
				}

				List<Integer> vegFoodDaysList = hostelVegDaysRepo.getVegFoodDays(hostelId);
				if (vegFoodDaysList.size() > 0) {
					returnObject.setVegFoodDays(vegFoodDaysList);
				} else {
					returnObject.setVegFoodDays(null);
				}

				List<HostelFacilities> hostelFacilityList = hostelFacilitiesRepo.getHostelFacilities(hostelId);
				if (hostelFacilityList.size() > 0) {
					List<FacilitiesDto> faciltiesReturnList = new ArrayList<FacilitiesDto>();
					for (HostelFacilities hostelFacility : hostelFacilityList) {
						FacilitiesDto temp = new FacilitiesDto();
						temp.setFacilityId(hostelFacility.getFacility().getId());
						if (hostelFacility.getFacility().getId() == 1) {
							returnObject.setMessFee(hostelFacility.getFee());
						}
						temp.setFacilityFee(hostelFacility.getFee());
						faciltiesReturnList.add(temp);
					}
					returnObject.setHostelFacilities(faciltiesReturnList);
				} else {
					returnObject.setHostelFacilities(null);
				}

				return returnObject;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	@Transactional
	@Override
	public List<HostelListResponse> getHostelsForOwner(Long userId) {
		Long ownerId = ownerRepo.getOwnerByUserId(userId);
		if (ownerId != null) {
			List<Hostel> tempList = hostelRepo.getHostelsByOwner(ownerId);

			if (tempList.size() > 0) {
				List<HostelListResponse> returnList = new ArrayList<HostelListResponse>();
				for (Hostel hostel : tempList) {
					HostelListResponse tempObject = new HostelListResponse();
					tempObject.setId(hostel.getId());
					tempObject.setName(hostel.getName());
					returnList.add(tempObject);
				}
				return returnList;
			} else {
				throw new CustomListException("25:no hostels owned by this owner");
			}
		} else {
			throw new CustomListException("17:user is not hostel owner");
		}
	}
}
