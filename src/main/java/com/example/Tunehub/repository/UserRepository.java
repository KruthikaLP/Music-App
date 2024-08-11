package com.example.Tunehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tunehub.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
   
	public Users  findByEmail(String email);
	

}
