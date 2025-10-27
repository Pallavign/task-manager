package com.pallavi.taskmanager.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * data transfer object for <strong> @Task </strong> entity. this <strong> DTO
 * </strong> is used for transferring task data between the application layers.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskManagerDTO {

	/**
	 * unique identifier for the task
	 */
	private String id;

	/**
	 * Title of the task
	 */
	@NotBlank(message = "Title is Mandatory")
	@Size(min = 10, max = 60, message = "Title must be atleast 10 characters " + "& must not exceed 60 characters.")
	@JsonProperty(required = true)
	private String title;

	/**
	 * description of the task
	 */
	@NotBlank(message = "Description is Mandatory")
	@Size(min = 25, max = 255, message = "Description must be atleast 25 characters "
			+ "& must not exceed 255 characters.")
	@JsonProperty(required = true)
	private String description;

	/**
	 * Due date of the task
	 */
	@Future(message = "Date must be in the Future")
	@JsonProperty(access = Access.READ_WRITE, required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String dueDate;

	/**
	 * <p>
	 * current status of the task (e.g., <strong><em> "Pending" </em></strong>,
	 * <strong><em> "In_Progress" </strong></em>, <strong><em> "Completed"
	 * </strong></em>)
	 * </p>
	 */
	private String status;

	/**
	 * Will be Ignored in API Response
	 */
	@JsonIgnore
	@JsonProperty(access = Access.READ_WRITE)
	private LocalDateTime taskCreatedAt;

	/**
	 * Will be Ignored in API Response
	 */
	@JsonIgnore
	@JsonProperty(access = Access.READ_WRITE)
	private LocalDateTime taskUpdatedAt;

	/**
	 * constructor for a <strong> @TaskDTO </strong> without timestamps. this can be
	 * useful when retrieving tasks form database.
	 *
	 * @param id          unique identifier for the task
	 * @param title       Title of the task
	 * @param description description of the task
	 * @param dueDate     Due date of the task
	 * @param status      current status of the task
	 */
	public TaskManagerDTO(String id, String title, String description, String dueDate, String status) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
	}
}
