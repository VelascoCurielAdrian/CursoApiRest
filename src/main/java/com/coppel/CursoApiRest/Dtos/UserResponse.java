package com.coppel.CursoApiRest.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

// INTERFACE ORDER
@Setter
@Getter
public class UserResponse implements Serializable {
	/**
	 *
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	// ORDER DE INSTANCE JSON
	private UUID id;
	private LocalDateTime creationDate;
	private Boolean enabled;
	private String email;
	private String name;
	private String lastName;
	private String role;

	public UserResponse(UUID id, LocalDateTime creationDate, Boolean boolean1, String email, String name, String lastName,
	                    String role) {
		this.id = id;
		this.creationDate = creationDate;
		this.enabled = boolean1;
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.role = role;
	}

}