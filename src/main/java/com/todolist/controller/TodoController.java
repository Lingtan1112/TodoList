package com.todolist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.business.TodoBusiness;
import com.todolist.model.Todo;

@RestController
@RequestMapping("/todo")
public class TodoController {  	            
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TodoBusiness todoBusiness;    
	  
	@GetMapping("/list")
	public ResponseEntity<List<Todo>> getTaskList() {
		List<Todo> todoList = todoBusiness.getTodoList(); 
		return new ResponseEntity<>(todoList, HttpStatus.OK); 
	}

	@PostMapping("/addtask")
	public ResponseEntity<Todo> addTask(Todo todo) {  
		System.out.println(todo);
		return new ResponseEntity<>(todo, HttpStatus.OK);     
	}
	
}
