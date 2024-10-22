package com.fss.Digital.Banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fss.Digital.Banking.entites.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByNomDuRole(String nomDuRole);
}
