package com.maissaude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MaissaudeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaissaudeApplication.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("123"));
	}

}