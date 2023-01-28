package com.todolist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.todolist.model.Todo;
import com.todolist.repo.TodoRepository;

@Repository
public class TodoDao {
	

	@Autowired
	private TodoRepository todoRepository;
	
	public List<Todo> getTodoList() {
		return todoRepository.findAll();
	}

	public Todo insertTodoList(Todo todo) {
		return todoRepository.save(todo);
	}

	public void deleteTask(long taskId) {
		todoRepository.deleteById(taskId);
	}

	public Todo updateTask(Todo todo) {
		return todoRepository.save(todo);
	}

}
