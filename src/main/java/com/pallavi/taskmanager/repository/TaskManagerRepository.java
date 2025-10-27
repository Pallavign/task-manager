package com.pallavi.taskmanager.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pallavi.taskmanager.dao.TaskManagerDAO;

@Repository
public interface TaskManagerRepository extends JpaRepository<TaskManagerDAO, UUID> {

}
