package com.andela.tms.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andela.tms.entities.Role;
import com.andela.tms.entities.RoleEnum;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	Optional<Role> findByName(RoleEnum name);
}
