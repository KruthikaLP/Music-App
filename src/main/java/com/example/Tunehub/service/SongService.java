package com.example.Tunehub.service;

import java.util.List;

import com.example.Tunehub.entities.Songs;

public interface SongService {

	public String addSong(Songs song);
	
	 public boolean findByName(String name);
	 
	 public List<Songs> fetchAllSongs();
	 
	 public void updateSong(Songs song);
	  
}
