package com.fss.Digital.Banking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.Digital.Banking.entites.Role;
import com.fss.Digital.Banking.repositories.RoleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role ajouterRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

}
