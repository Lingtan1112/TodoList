package com.todolist.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="todo")
public class Todo {

	@Override
	public String toString() {
		return "Todo [taskId=" + taskId + ", taskName=" + taskName + ", taskEndDate=" + taskEndDate + ", taskStatus="
				+ taskStatus + "]";
	}
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long taskId;
	
	
	private String taskName;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)	
	private LocalDate taskEndDate;
	
	public LocalDate getTaskEndDate() {
		return taskEndDate;
	}
	
	public void setTaskEndDate(LocalDate taskEndDate) { 
		this.taskEndDate = taskEndDate;
	}
	private String taskStatus;
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
}
