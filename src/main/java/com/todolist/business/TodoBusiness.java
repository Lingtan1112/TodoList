package com.todolist.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.dao.TodoDao;
import com.todolist.model.Todo;

@Service
public class TodoBusiness {
	
	@Autowired
	private TodoDao todoDao;

	public List<Todo> getTodoList() {  
		return todoDao.getTodoList();
	}
	  
	public Todo insertTodoList(Todo todo) throws Exception {  
		Todo todoData = null;
		if(todo.getTaskName()!=null) {
			todoData = todoDao.insertTodoList(todo);
		}else {
			throw new Exception("Cannot save empty body");
		}
		
		return todoData;
	}

	public void deleteTask(long taskId) throws Exception { 
		try {
			todoDao.deleteTask(taskId);
		}catch(Exception e) {
			throw new Exception("Exception occured");
		}
		
	}

	public Todo updateTask(Todo todo) { 
		return todoDao.updateTask(todo);
	}
}
