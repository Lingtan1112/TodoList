package com.todolist.business;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todolist.dao.UserDao;
import com.todolist.model.UserData;

@Service
public class UserService  implements UserDetailsService{

	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Collection<GrantedAuthority> listOfAuth = new ArrayList<>();
		UserData udata = userDao.getUserByUserName(username);
		if(udata ==null) {
			throw new UsernameNotFoundException(username+" not found");
		}
		listOfAuth.add(new SimpleGrantedAuthority(udata.getRole() != null ? udata.getRole() : "USER"));
		return new User(udata.getUserName(), udata.getPassword(),listOfAuth);
	}
	
}
