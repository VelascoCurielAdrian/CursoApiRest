package com.coppel.CursoApiRest.Controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coppel.CursoApiRest.Dtos.UserCreatedRequest;
import com.coppel.CursoApiRest.Dtos.UserList;
import com.coppel.CursoApiRest.Dtos.UserUpdateRequest;
import com.coppel.CursoApiRest.Models.User;
import com.coppel.CursoApiRest.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private final UserService userService;

	List<String> roles;

	public UserController(UserService userService) {
		this.userService = userService;
		roles = Arrays.asList("ADMINISTRADOR", "DEVELOPER");
	}

	// RUTA PARA LISTADO CON PAGINACIÓN
	@GetMapping("/listado")
	public ResponseEntity<Object> getUsersResponse(@RequestParam(required = false, defaultValue = "0") int page,
	                                               @RequestParam(required = false, defaultValue = "20") int size,
	                                               @RequestParam(required = false, defaultValue = "creationDate,desc") String[] sort) {

		// sort[0] - ATTRIBUTES ORDER
		// sort[1] - ORDER DESC, ORDER ASC,

		Page<User> userPage = userService.getUsers(page, size, sort);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-elements", String.valueOf(userPage.getTotalElements()));

		UserList response = new UserList();
		response.setRows(userPage.getContent());
		response.setCount(userPage.getTotalElements());

		return ResponseEntity.ok().headers(headers).body(response);
	}

	// RUTA PARA OBTENER UN USUARIO POR ID
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable UUID id) {
		return userService.getUserById(id);
	}

	// RUTA PARA ACTUALIZAR UN USUARIO
	@PutMapping("/{id}")
	public ResponseEntity<User> putUser(@PathVariable UUID id, @Valid @RequestBody UserUpdateRequest userUpdate) {
		return userService.putUser(id, userUpdate);
	}

	// RUTA PARA CREAR UN USUARIO
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody UserCreatedRequest createUserRequest) {
		return userService.createUser(createUserRequest);
	}
}
