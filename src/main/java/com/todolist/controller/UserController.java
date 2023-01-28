package com.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.business.JWTGenerator;
import com.todolist.business.UserService;
import com.todolist.dto.AuthDto;
import com.todolist.dto.RegisterDto;
import com.todolist.model.UserData;
import com.todolist.repo.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	BCryptPasswordEncoder passworEncoder;
	
	@Autowired
	JWTGenerator jwtGenerator;
	
	
	
	@Autowired
	UserService userService;

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
	
	@GetMapping("/finduser")
	public ResponseEntity<UserDetails> findUser(@RequestParam("userName") String userName){
		UserDetails data = null;
		try {
			data = userService.loadUserByUsername(userName);
			System.out.println(data);
			
		} catch (Exception e) {
			e.printStackTrace();
		}   
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@GetMapping("/login")
	public ResponseEntity<AuthDto> login(@RequestBody RegisterDto registerDto) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(registerDto.getUserName(), registerDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtGenerator.generateToken(authentication);
		AuthDto authDto = new AuthDto(token, "Bearer");
		return new ResponseEntity<>(authDto, HttpStatus.OK);
	}
	
	
	@GetMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
		
		UserData userData = new UserData();
		userData.setUserName(registerDto.getUserName());
		userData.setPassword(passworEncoder.encode(registerDto.getPassword()));
		userData.setRole(registerDto.getRole());
		
		userRepo.save(userData);
		return new ResponseEntity<>("Successfully Registered in", HttpStatus.OK);
	}
}
