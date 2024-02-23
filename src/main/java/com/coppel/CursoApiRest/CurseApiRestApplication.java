package com.coppel.CursoApiRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurseApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurseApiRestApplication.class, args);
		
		String message = "COMPILATION SUCCESSFUL";
		int length = message.length();
		System.out.println("+" + "-".repeat(length + 2) + "+");
		System.out.println("| " + message + " |");
		System.out.println("+" + "-".repeat(length + 2) + "+");
	}
}
