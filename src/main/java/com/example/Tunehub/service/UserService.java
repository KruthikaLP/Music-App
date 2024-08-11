package com.example.Tunehub.service;

import com.example.Tunehub.entities.Users;

public interface UserService {
	
  public String addUser(Users user);
  
  public boolean findByEmail(String email);
  
  public boolean findPassword(String email,String password);

  public String findRole(String email);

public Users getUser(String email);

public void updateUser(Users user);
  
}
