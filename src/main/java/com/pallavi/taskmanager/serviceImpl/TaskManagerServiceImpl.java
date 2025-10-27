package com.pallavi.taskmanager.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pallavi.taskmanager.dao.TaskManagerDAO;
import com.pallavi.taskmanager.dto.TaskManagerDTO;
import com.pallavi.taskmanager.repository.TaskManagerRepository;
import com.pallavi.taskmanager.service.TaskManagerService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class TaskManagerServiceImpl implements TaskManagerService {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskManagerServiceImpl.class);

	TaskManagerRepository taskManagerRepository;
	
	@Autowired
	private void TaskManagerServiceImpl(TaskManagerRepository taskManagerRepository) {
		this.taskManagerRepository=taskManagerRepository;
	}
	@Override
	public List<TaskManagerDTO> getAllTasks() {
		logger.info("Retrieving all tasks.");
		return taskManagerRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	private TaskManagerDTO convertToDTO(TaskManagerDAO task) {
		logger.debug("Converting Task entity to TaskDTO: {} ", task);
		return new TaskManagerDTO(task.getId().toString(), task.getTitle(), task.getDescription(),
				task.getDueDate().toString(), task.getStatus().toString());
	}
	

}
