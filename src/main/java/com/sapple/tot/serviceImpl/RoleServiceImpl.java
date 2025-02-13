package com.sapple.tot.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapple.tot.entities.Role;
import com.sapple.tot.repositories.RoleRepository;
import com.sapple.tot.responseDto.RolesResponse;
import com.sapple.tot.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepo;

	@Transactional
	@Override
	public List<RolesResponse> getRoles() {
		List<Role> tempList = roleRepo.findAll();
		List<RolesResponse> returnList = new ArrayList<RolesResponse>();
		for (Role role : tempList) {
			RolesResponse returnRole = new RolesResponse();
			returnRole.setId(role.getId());
			returnRole.setName(role.getName());
			returnList.add(returnRole);
		}
		return returnList;
	}
}
