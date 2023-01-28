package com.todolist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.business.TodoBusiness;
import com.todolist.model.Todo;

@RestController
@RequestMapping("/todo") 
@CrossOrigin(origins = "http://localhost:4040")
public class TodoController {  	            
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TodoBusiness todoBusiness; 
	
	@GetMapping("/welcome")
	@CrossOrigin(origins = "http://localhost:4040")
	public String welcomePage() {
		return "Welcome";      
	}
	  
	@GetMapping("/list")
	public ResponseEntity<List<Todo>> getTaskList() {
		List<Todo> todoList = todoBusiness.getTodoList(); 
		return new ResponseEntity<>(todoList, HttpStatus.OK);      
	}

	@PostMapping("/addtask")
	public ResponseEntity<Todo> addTask(@RequestBody Todo todo) throws Exception {  
		todoBusiness.insertTodoList(todo);   
		return new ResponseEntity<>(todo, HttpStatus.OK);           
	}
	 
	@PostMapping("/updatetask")
	public ResponseEntity<Todo> updateTask(@RequestBody Todo todo) {  
		Todo updatedData = todoBusiness.updateTask(todo);            
		return new ResponseEntity<>(updatedData, HttpStatus.OK);            
	}
	
	@PostMapping("/deletetask/{taskId}")
	public ResponseEntity<Map<String, Object>> deleteTask(@PathVariable long taskId) throws Exception {  
		
		Map<String, Object> response = new HashMap<>();
		try{
			todoBusiness.deleteTask(taskId); 
			response.put("message", "Successfully deleted");
			response.put("responseCode",  HttpStatus.OK);
		}catch(Exception e) {
			throw new Exception("Id Not found");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);              
	}
	
	
}
