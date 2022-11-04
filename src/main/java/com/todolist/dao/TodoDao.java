package com.todolist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TodoDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getTodoList() {
		String sql = "select task_id as taskId, task_name as taskName, task_status as taskStatus, task_end_date as taskEndDate from todo";
		List<Map<String, Object>> taskList = jdbcTemplate.queryForList(sql);
		return taskList;
	}

}
