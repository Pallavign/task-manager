package com.pallavi.taskmanager.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pallavi.taskmanager.dto.TaskManagerDTO;
import com.pallavi.taskmanager.service.TaskManagerService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/tasks")
public class TaskManagerController {
	private static final Logger logger = LoggerFactory.getLogger(TaskManagerController.class);

	private TaskManagerService taskManagerService;

	/**
	 * @Constructor for <strong> @TaskController </strong>
	 * 
	 * @param service - the <strong> @TaskService </strong> to be used for task
	 *                operations
	 */
	public TaskManagerController(TaskManagerService service) {
		this.taskManagerService = service;
	}

	/**
	 * Retrieve all tasks.
	 *
	 * @return a list of <strong> @TaskDTO </strong> representing all tasks if
	 *         found. otherwise a message <strong> "No tasks found, please create a
	 *         few tasks" </strong> will be sent to the client.
	 */
	@GetMapping
	public ResponseEntity<Object> getAllTasks() {
		logger.info("Fetching all tasks");
		List<TaskManagerDTO> tasks = taskManagerService.getAllTasks();
		if (tasks.isEmpty()) {
			Map<String, String> response = Map.of("message", "No tasks found, please create a few tasks.");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(tasks);
	}
	
	
}
