package com.todolist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.todolist.model.UserData;
import com.todolist.repo.UserRepository;

@Component
public class UserDao {

	@Autowired
	UserRepository userRepo;
	
	public UserData getUserByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}
}
