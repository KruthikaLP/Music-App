package com.example.Tunehub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tunehub.entities.Users;
import com.example.Tunehub.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository userrepo;

	@Override
	public String addUser(Users user) {
		userrepo.save(user);
		return "object created";
	}

	@Override
	public boolean findByEmail(String email) {
		if(userrepo.findByEmail(email)==null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public boolean findPassword(String email,String password) {
		Users user = userrepo.findByEmail(email);
		if(user.getPassword().equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String findRole(String email) {
		return userrepo.findByEmail(email).getRole();
	}

	@Override
	public Users getUser(String email) {
		return userrepo.findByEmail(email);
	
	}

	@Override
	public void updateUser(Users user) {
		userrepo.save(user);
	}

}
