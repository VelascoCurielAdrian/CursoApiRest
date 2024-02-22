package com.coppel.CursoApiRest.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="USERS")
public class User {
	@Id
	@GeneratedValue
	@Column(columnDefinition="RAW(16)")
	private UUID id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ENABLED")
	private Boolean enabled;
	
	@Column(name="ROLE")
	private String role;
	
	@Column(name="VACATION_START")
	private LocalDateTime vacationStart;
	
	@Column(name="VACATION_ENDING")
	private LocalDateTime vacationEnding;
	
	@Column(name="CREATION_DATE")
	private LocalDateTime creationDate;
	
	@Column(name="MODIFICATION_DATE")
	private LocalDateTime modificationDate;
	
	public User() {}

}
