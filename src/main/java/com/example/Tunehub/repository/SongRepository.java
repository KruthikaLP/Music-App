package com.example.Tunehub.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tunehub.entities.Songs;

public interface SongRepository extends JpaRepository<Songs, Integer> {
	
	public Songs findByName(String name);
	
	


}
