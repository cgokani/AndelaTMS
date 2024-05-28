package com.andela.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andela.tms.entities.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer>,JpaRepository<Task, Integer>,
JpaSpecificationExecutor<Task>{


}
