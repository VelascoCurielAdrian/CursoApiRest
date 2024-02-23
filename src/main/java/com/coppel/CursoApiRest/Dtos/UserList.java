package com.coppel.CursoApiRest.Dtos;

import java.util.ArrayList;
import java.util.List;

import com.coppel.CursoApiRest.Models.User;
import lombok.Getter;
import lombok.Setter;


@Setter
public class UserList {
	private List<User> rows;
	@Getter
	private int count;

	// Getters y Setters
	public List<UserResponse> getRows() {
		List<UserResponse> userResponses = new ArrayList<>();
		for (User user : rows) {
			UserResponse userResponse = new UserResponse(
					user.getId(),
					user.getCreationDate(),
					user.getEnabled(),
					user.getEmail(),
					user.getName(),
					user.getLastName(),
					user.getRole()
			);
			userResponses.add(userResponse);
		}
		return userResponses;
	}

}
