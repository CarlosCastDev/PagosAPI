package com.bankXx.pagosAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankXx.pagosAPI.service.PocJMCService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/PocJMC")
public class PocJMCController {

	private final PocJMCService pocJMTService;

	@Autowired
	public PocJMCController(PocJMCService pocJMTService) {
		this.pocJMTService = pocJMTService;
	}

	@Operation(summary = "provocar un memoryLeak o deadlock (1 memoryLeak 1 deadlock - notesteado)")
	@GetMapping("/{id}")
	public ResponseEntity<HttpStatus> test(@PathVariable int id) {
		pocJMTService.test(id);
		return ResponseEntity.ok(HttpStatus.ACCEPTED);
	}
}
