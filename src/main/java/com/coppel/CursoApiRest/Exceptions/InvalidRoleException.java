package com.coppel.CursoApiRest.Exceptions;

import java.io.Serial;

public class InvalidRoleException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public InvalidRoleException(String msg) {
		super(msg);
	}
}
