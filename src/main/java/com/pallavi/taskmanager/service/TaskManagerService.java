package com.pallavi.taskmanager.service;

import java.util.List;
import java.util.Optional;

import com.pallavi.taskmanager.dto.CreateTaskDTO;
import com.pallavi.taskmanager.dto.CreateTaskResponse;
import com.pallavi.taskmanager.dto.TaskManagerDTO;

import jakarta.validation.Valid;

public interface TaskManagerService {

	List<TaskManagerDTO> getAllTasks();

	 Optional<TaskManagerDTO> getTaskById(String id);

	CreateTaskResponse createANewTask(@Valid CreateTaskDTO createTaskDTO);

}
