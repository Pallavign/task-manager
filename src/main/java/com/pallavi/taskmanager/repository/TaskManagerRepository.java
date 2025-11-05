package com.pallavi.taskmanager.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import com.pallavi.taskmanager.dao.Status;
import com.pallavi.taskmanager.dao.TaskManagerDAO;

@Repository
public interface TaskManagerRepository extends JpaRepository<TaskManagerDAO, UUID> {

	/**
	 * Finds all tasks with the specified status.
	 * 
	 * @param @Status - the status of the tasks to retrieve
	 * @return a list <strong> @List </strong> <strong> @Task </strong> of tasks
	 *         that match the given status
	 */
	List<TaskManagerDAO> findByStatus(Status status);

}