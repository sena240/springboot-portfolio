package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MasterController {
	@GetMapping("/master/list")
	public String showMasterPage() {
		return "master/list";
	}

}
