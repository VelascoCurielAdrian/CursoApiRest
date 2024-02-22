package com.coppel.CursoApiRest.Services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coppel.CursoApiRest.Dtos.UserCreatedRequest;
import com.coppel.CursoApiRest.Dtos.UserUpdateRequest;
import com.coppel.CursoApiRest.Exceptions.EmailAlreadyExistsException;
import com.coppel.CursoApiRest.Exceptions.InvalidRoleException;
import com.coppel.CursoApiRest.Exceptions.FoundException;
import com.coppel.CursoApiRest.Models.User;
import com.coppel.CursoApiRest.Repositorys.UserRepository;


@Service
public class UserService implements IUserservice {
	@Setter
	@Autowired
	private UserRepository userRepository;
	List<String> roles = Arrays.asList("ADMINISTRADOR", "DEVELOPER");

	// SERVICE LIST PAGINATE
	@Override
	public Page<User> getUsers(int page, int size, String[] sort) {
		List<Sort.Order> orders = new ArrayList<>();
		if (sort[1].equals("desc")) {
			orders.add(new Sort.Order(Sort.Direction.DESC, sort[0]));
		} else {
			orders.add(new Sort.Order(Sort.Direction.ASC, sort[0]));
		}

		PageRequest pagingSort = PageRequest.of(page, size, Sort.by(orders));
		return userRepository.findAll(pagingSort);
	}

	@Override
	public Optional<User> getUser(UUID id) {
		return Optional.empty();
	}

	@Override
	public ResponseEntity<User> putUser(UUID id, UserUpdateRequest userUpdate) {
		Optional<User> userOptional = userRepository.findById(id);
		ResponseEntity<User> response;

		if (userOptional.isPresent()) {
			User user = userOptional.get();

			if (userRepository.findByEmailIgnoreCase(userUpdate.getEmail()) == null) {
				user.setEmail(userUpdate.getEmail());
			} else {
				throw new EmailAlreadyExistsException("Email already exist!");
			}

			user.setName(userUpdate.getName());
			user.setLastName(userUpdate.getLastName());

			if (userUpdate.getVacationStart() != null) {
				user.setVacationStart(userUpdate.getVacationStart());
			}

			if (userUpdate.getVacationEnding() != null) {
				user.setVacationEnding(userUpdate.getVacationEnding());
			}
			if (userUpdate.getRole() != null) {
				if (roles.stream().anyMatch(userUpdate.getRole()::contains)) {
					user.setRole(userUpdate.getRole());
				} else {
					throw new InvalidRoleException("Invalid Role");
				}
			}

			user.setModificationDate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));

			response = new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);

		} else {
			throw new FoundException("Not Found");
		}
		return response;
	}

	@Override
	public ResponseEntity<User> createUser(UserCreatedRequest createUserRequest) {
		User newUser = new User();
		newUser.setId(UUID.randomUUID());
		newUser.setEmail(createUserRequest.getEmail());
		newUser.setName(createUserRequest.getName());
		newUser.setLastName(createUserRequest.getLastName());
		newUser.setRole(createUserRequest.getRole());

		if (createUserRequest.getVacationStart() != null) {
			newUser.setVacationStart(createUserRequest.getVacationStart());
		}

		if (createUserRequest.getVacationEnding() != null) {
			newUser.setVacationEnding(createUserRequest.getVacationEnding());
		}

		newUser.setCreationDate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));

		User savedUser = userRepository.save(newUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	@Override
	public Optional<User> getUserById(UUID id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user;
		} else {
			throw new FoundException("Not Found");
		}
	}

}
