package com.pallavi.taskmanager.dao;

import org.springframework.scheduling.config.Task;

public enum Status {
	/* * Task is created but not started
	 */
	PENDING,

	/**
	 * Task is currently being worked on
	 */
	IN_PROGRESS,

	/**
	 * Task has been completed
	 */
	COMPLETED;
}
