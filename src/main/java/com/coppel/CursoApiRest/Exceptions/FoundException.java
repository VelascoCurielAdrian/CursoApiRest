package com.coppel.CursoApiRest.Exceptions;

import java.io.Serial;

public class FoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;
	
	public FoundException(String msg) {
		super(msg);
	}
}
