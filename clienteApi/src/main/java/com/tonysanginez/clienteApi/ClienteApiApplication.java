package com.tonysanginez.clienteApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class ClienteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteApiApplication.class, args);
	}

}
