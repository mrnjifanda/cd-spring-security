package com.njifanda.ssa.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.njifanda.ssa.Models.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	
	List<Role> findAll();

	List<Role> findByName(String roleName);
}
