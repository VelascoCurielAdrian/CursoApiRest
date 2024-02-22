package com.coppel.CursoApiRest.Repositorys;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coppel.CursoApiRest.Models.User;


public interface UserRepository extends JpaRepository<User, UUID> {
	User findByEmailIgnoreCase(String email);

	Page<User> findAll(Pageable pageable);

	List<User> findAll(Sort sort);
}

