package com.coppel.CursoApiRest.Dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUpdateRequest {
	@NotNull
	@Email
	private String email;
	@NotBlank
	@NotNull
	private String name;
	@NotBlank
	@NotNull
	private String lastName;
	private LocalDateTime vacationStart;
	private LocalDateTime vacationEnding;

//	@Pattern(regexp = "(DEVELOPER|ADMINISTRATOR)$")
	private String role;
}
