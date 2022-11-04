package com.todolist.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Todo {

	@Override
	public String toString() {
		return "Todo [taskId=" + taskId + ", taskName=" + taskName + ", taskEndDate=" + taskEndDate + ", taskStatus="
				+ taskStatus + "]";
	}
	private int taskId;
	private String taskName;
	private Date taskEndDate;
	private boolean taskStatus;
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getTaskEndDate() {
		return taskEndDate;
	}
	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}
	public boolean isTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(boolean taskStatus) {
		this.taskStatus = taskStatus;
	}
	
}
