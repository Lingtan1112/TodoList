package com.todolist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolist.model.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {

	public UserData findByUserName(String userName);
	
}
