package com.pallavi.taskmanager.dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Entity class representing a task.
 * 
 *         this class defines the structure of a task, including its
 *         <strong><em> @UUID, title, description, due date, status, and
 *         timestamps for creation and updates </em></strong>.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Task")
@Table(name = "tasks", uniqueConstraints = @UniqueConstraint(columnNames = { "task_id" }, name = "task_id_unique"))
public class Task implements Serializable {

	private static final long serialVersionUID = -9102496759180974133L;

	/**
	 * unique identifier for the task.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@UuidGenerator(style = Style.RANDOM)
	@JdbcTypeCode(value = java.sql.Types.VARCHAR)
	@Column(name = "task_id", updatable = false, nullable = false, unique = true, columnDefinition = "VARCHAR(36)", length = 36)
	private UUID id;

	/**
	 * Title of the task.
	 */
	@Column(name = "task_title", nullable = false, columnDefinition = "VARCHAR(60)")
	private String title;

	/**
	 * description of the task.
	 */
	@Column(name = "task_description", nullable = false, columnDefinition = "VARCHAR(255)")
	private String description;

	/**
	 * Due date of the task.
	 */
	@Column(name = "task_due_date", nullable = false, columnDefinition = "DATE")
	@Temporal(value = TemporalType.DATE)
	private LocalDate dueDate;

	/**
	 * <strong> @Status </strong> of the task (<strong><em>PENDING</em></strong>,
	 * <strong><em>IN_PROGRESS</em></strong>, <strong><em>COMPLETED</em></strong>).
	 */
	@Column(name = "task_status", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Status status;

	/**
	 * timestamp when the task was created.
	 */
	@Column(name = "task_created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
	@CreationTimestamp(source = SourceType.VM)
	private LocalDateTime createdAt;

	/**
	 * timestamp when the task was last updated.
	 */
	@Column(name = "task_updated_at", columnDefinition = "TIMESTAMP")
	@UpdateTimestamp(source = SourceType.VM)
	private LocalDateTime updatedAt;

	/**
	 * @return the current local date and time.
	 */
	private LocalDateTime localDateTime() {
		LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
		return now;
	}

	/**
	 * <p>
	 * method called before persisting a new task.
	 * </p>
	 * 
	 * <p>
	 * sets the creation and update timestamps, decided by the <strong><em> Java
	 * Virtual Machine </em></strong>, and initializes the status to <strong>
	 * PENDING </strong>.
	 * </p>
	 */
	@PrePersist
	private void onPrePersist() {
		this.createdAt = localDateTime();
		this.updatedAt = localDateTime();
		this.status = Status.PENDING;
	}

	/**
	 * <p>
	 * method called before updating an existing task.
	 * <p>
	 * 
	 * <p>
	 * Updates the update timestamp.
	 * </p>
	 */
	@PreUpdate
	private void onPreUpdate() {
		this.updatedAt = localDateTime();
	}
}
