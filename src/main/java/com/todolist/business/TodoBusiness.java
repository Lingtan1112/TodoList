package com.todolist.business;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.dao.TodoDao;
import com.todolist.model.Todo;

@Service
public class TodoBusiness {
	
	@Autowired
	private TodoDao todoDao;

	public List<Todo> getTodoList() {  

		List<Todo> todoListToReturn = new ArrayList<>();
		
		List<Map<String, Object>> todoList = todoDao.getTodoList();
		if (todoList != null) {
			for (Map<String, Object> listItem : todoList) {
				Todo todo = new Todo();
				todo.setTaskId((Integer) listItem.get("TASKID"));
				todo.setTaskName((String) listItem.get("TASKNAME"));
				todo.setTaskEndDate(((Date) listItem.get("TASKENDDATE")).toLocalDate()); 
				todo.setTaskStatus((String) listItem.get("TASKSTATUS"));
				todoListToReturn.add(todo);  
			}
		}
		return todoListToReturn;
	}
	  
	public List<Todo> insertTodoList(Todo todo) {  
		
		if(todo.getTaskName()!=null) {
			todoDao.insertTodoList(todo);
		}
		
		return null;
	}

	public int deleteTask(int taskId) { 
		return todoDao.deleteTask(taskId);
	}

	public int updateTask(Todo todo) { 
		return todoDao.updateTask(todo);
	}
}
