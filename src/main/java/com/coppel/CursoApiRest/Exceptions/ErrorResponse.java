package com.coppel.CursoApiRest.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
	private long timestamp;
	private int status;
	private String error;
	private String message;
	private String path;

}
