package com.pallavi.taskmanager.dao;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* UsersDAO
*
*/
@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UsersDAO {
	

	@Column(name = "UUID", updatable = false)
	private UUID id;

	@Id
	@Column(name = "user_name")
	private String userName;

	@Column(name = "display_name")
	private String displayName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "modified_date")
	private Timestamp modifiedDate;
	
	@Column(name = "created_by", columnDefinition = "varchar(500)")
	private String createdBy;
	
	@Column(name = "last_updated_by", columnDefinition = "varchar(500)")
	private String lastUpdatedBy;
	
}
