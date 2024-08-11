package com.example.Tunehub.service;

import java.util.List;

import com.example.Tunehub.entities.PlayList;
import com.example.Tunehub.entities.Songs;

public interface PlayListService {
	
	public void addPlayList(PlayList playlist);
	
	public List<PlayList> fetchPlayList();
}
