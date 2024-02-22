package com.coppel.CursoApiRest.Services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.coppel.CursoApiRest.Dtos.UserCreatedRequest;
import com.coppel.CursoApiRest.Dtos.UserUpdateRequest;
import com.coppel.CursoApiRest.Models.User;

public interface IUserservice {
	Page<User> getUsers(int page, int size, String[] sort);

	Optional<User> getUser(UUID id);

	ResponseEntity<User> putUser(UUID id, UserUpdateRequest userUpdateRequest);

	ResponseEntity<User> createUser(UserCreatedRequest createUserRequest);

	Optional<User> getUserById(UUID id);

}
