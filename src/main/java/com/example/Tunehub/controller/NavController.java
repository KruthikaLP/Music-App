package com.example.Tunehub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Controller 
//its used to connect with html files in springboot

public class NavController {
 
	
	@GetMapping("/home")
	public String home() {
		return "home1";
	}
	
	@GetMapping("/map-regist")
	public String regist() {
		return "Registration";
	}
	
	@GetMapping("/map-login")
	public String login() {
		return "Login";
	}
	
	@GetMapping("/map-songsadd")
	public String addSongs() {
		return"addSongs";
	}
	
	
	
	
	
}

