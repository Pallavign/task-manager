package com.pallavi.taskmanager.dto;

import java.time.LocalDate;

import org.springframework.lang.Nullable;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTaskDTO {

	/**
	 * Title of the task. must be between 10 and 60 characters.
	 */
	@NotBlank(message = "Title is Mandatory")
	@Size(min = 10, max = 60, message = "Title must be atleast 10 characters " + "& must not exceed 60 characters.")
	@JsonProperty(required = true)
	private String title;

	/**
	 * description of the task must be between 25 and 255 characters.
	 */
	@NotBlank(message = "Description is Mandatory")
	@Size(min = 25, max = 255, message = "Description must be atleast 25 characters "
			+ "& must not exceed 255 characters.")
	@JsonProperty(required = true)
	private String description;

	/**
	 * Due date of the task. this class member is optional for creating a new task
	 * and must be in the @Future if provided.
	 */
	@Future(message = "Date must be in the Future")
	@JsonProperty(access = Access.READ_WRITE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Nullable
	private LocalDate dueDate;
}
