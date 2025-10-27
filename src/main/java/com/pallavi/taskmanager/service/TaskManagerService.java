package com.pallavi.taskmanager.service;

import java.util.List;

import com.pallavi.taskmanager.dto.TaskManagerDTO;

public interface TaskManagerService {

	List<TaskManagerDTO> getAllTasks();

}
