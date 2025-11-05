package com.pallavi.taskmanager.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pallavi.taskmanager.dao.TaskManagerDAO;
import com.pallavi.taskmanager.dto.CreateTaskDTO;
import com.pallavi.taskmanager.dto.CreateTaskResponse;
import com.pallavi.taskmanager.dto.TaskManagerDTO;
import com.pallavi.taskmanager.exception.TaskNotFoundException;
import com.pallavi.taskmanager.repository.TaskManagerRepository;
import com.pallavi.taskmanager.service.TaskManagerService;
import com.pallavi.taskmanager.utils.UUIDConverter;

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
	@Override
	public Optional<TaskManagerDTO> getTaskById(String id) {
		logger.info("Retrieving task with ID: {} ", id);
		UUID uuid = UUIDConverter.stringToUUIDConverter(id);
		if (!taskManagerRepository.findById(uuid).isPresent()) {
			logger.error("Task Not Found with the given UUID: {} ", id);
			throw new TaskNotFoundException("Task Not Found with the given UUID : " + id);
		}
		return taskManagerRepository.findById(uuid).map(this::convertToDTO);
	}
	@Override
	@Transactional
	@Modifying
	public CreateTaskResponse createANewTask(CreateTaskDTO createTask) {
		logger.info("Creating a new task with details: {} ", createTask);
		LocalDate dueDate = (createTask.getDueDate() != null) ? createTask.getDueDate() : LocalDate.now().plusDays(7);
		TaskManagerDAO task = convertToEntity(createTask);
		task.setDueDate(dueDate);
		TaskManagerDAO savedTask = taskManagerRepository.save(task);
		logger.info("Task created successfully with ID: {} ", savedTask.getId());
		return convertToCreateTaskResponse(savedTask);
	}
	private CreateTaskResponse convertToCreateTaskResponse(TaskManagerDAO task) {
		logger.debug("Converting Task entity to CreateTaskResponse: {} ", task);
		return new CreateTaskResponse(task.getId().toString(), task.getTitle(), task.getDescription(),
				task.getDueDate().toString(), task.getStatus().toString());
	}
	
	private TaskManagerDAO convertToEntity(CreateTaskDTO createTaskDTO) {
		logger.debug("Converting CreateTaskRequestDTO to Task entity: {} ", createTaskDTO);
		TaskManagerDAO task = new TaskManagerDAO();
		task.setTitle(createTaskDTO.getTitle());
		task.setDescription(createTaskDTO.getDescription());
		return task;
	}
	

}
