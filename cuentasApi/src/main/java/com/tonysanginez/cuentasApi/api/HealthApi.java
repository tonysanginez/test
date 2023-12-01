package com.tonysanginez.cuentasApi.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/health")
public class HealthApi {
	
	@GetMapping
	public ResponseEntity<?> health() {
		return new ResponseEntity<>("Health OK", HttpStatus.OK);
	}
	
}