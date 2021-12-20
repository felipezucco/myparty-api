package com.tendelfc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tendelfc.entity.Organizador;

@RestController
@RequestMapping("/")
public class StartController {

	@GetMapping
	public Organizador teste() {
		Organizador org = new Organizador();
		org.setId(2l);
		org.setName("dasda");
		return org;
	}
	
}
