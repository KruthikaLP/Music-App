package com.example.Tunehub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tunehub.entities.Songs;
import com.example.Tunehub.repository.SongRepository;

@Service
public class SongServiceImplementation implements SongService {
	
	@Autowired
	SongRepository songrepo;

	@Override
	public String addSong(Songs song) {
		songrepo.save(song);
		return "song added";
	}

	@Override
	public boolean findByName(String name) {
		Songs song = songrepo.findByName(name);
		if(song==null) {
			return false;
		}
		else {
			return true;
		}
		
	}

	@Override
	public List<Songs> fetchAllSongs() {
		List<Songs> listsong = songrepo.findAll();
		return listsong;
	}

	@Override
	public void updateSong(Songs song) {
		songrepo.save(song);	
	}


}
