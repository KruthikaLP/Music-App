package com.example.Tunehub.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tunehub.entities.PlayList;

import com.example.Tunehub.repository.PlayRepository;


@Service
public class PlayListServiceImplementation implements PlayListService {
	
	@Autowired
	PlayRepository prepo;

	@Override
	public void addPlayList(PlayList playlist) {
		prepo.save(playlist);
	}

	@Override
	public List<PlayList> fetchPlayList() {
		return prepo.findAll();
	}

	

}
