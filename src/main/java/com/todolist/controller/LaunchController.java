package com.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class LaunchController {   
	      
	@GetMapping("/")
	public String launch(Model model) {   
		return "index";
	}
	
}
