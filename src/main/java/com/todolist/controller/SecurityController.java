package com.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.model.UserData;
import com.todolist.repo.UserRepository;

@RestController
@RequestMapping("/security")
public class SecurityController {
	

	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/allow")
	public String allowed() {
		return "allowed";
	}
	
	@GetMapping("/notallowed")
	public String notAllowed() {
		return "not allowed";
	}
	


	@GetMapping("/showuser")
	public ResponseEntity<List<UserData>> getUsers(){
		List<UserData> data = null;
		try {
			data = userRepo.findAll();
			System.out.println(data);
			
		} catch (Exception e) {
			e.printStackTrace();
		}   
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	
}
