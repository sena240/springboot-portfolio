package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/error")
	public String error() {
		return "error";
	}


	@GetMapping("/")
	public String loginHome(Authentication loginUser, Model model) {
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("authority", loginUser.getAuthorities());
		return "home/list";
	}
}
