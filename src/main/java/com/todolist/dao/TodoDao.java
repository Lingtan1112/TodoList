package com.todolist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.todolist.model.Todo;

@Repository
public class TodoDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getTodoList() {
		String sql = "select task_id as taskId, task_name as taskName, task_status as taskStatus, task_end_date as taskEndDate from todo";
		List<Map<String, Object>> taskList = jdbcTemplate.queryForList(sql);
		return taskList;
	}

	public void insertTodoList(Todo todo) {
		String sql = "insert into todo (task_name, task_end_date, task_status) values (?,?,?)";
		int insertedData = jdbcTemplate.update(sql, todo.getTaskName(),todo.getTaskEndDate(),todo.getTaskStatus());
		System.out.println(insertedData);
	}

	public int deleteTask(int taskId) {
		String sql = "delete from todo where task_id = (?)";
		int deletedLength = jdbcTemplate.update(sql,taskId);
		return deletedLength;
	}

	public int updateTask(Todo todo) {
		String sql = "update todo set task_name = ?, task_end_date = ?, task_status = ? where task_id = (?)";
		int deletedLength = jdbcTemplate.update(sql,todo.getTaskName(), todo.getTaskEndDate(),todo.getTaskStatus(), todo.getTaskId());
		return deletedLength;
	}

}
