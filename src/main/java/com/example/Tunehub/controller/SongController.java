package com.example.Tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Tunehub.entities.Songs;
import com.example.Tunehub.service.SongService;

@CrossOrigin("*")
@Controller
public class SongController {

	@Autowired
	SongService songserv;

	@PostMapping("/map-addsongs")
	public String addSong(@ModelAttribute Songs song) {
		boolean status = songserv.findByName(song.getName());
		if(status==false) {
			songserv.addSong(song);
			return "songAddedSuccessfully";
		}
		else {
			return "songaddedfail";
		}
		
	}
	
	@GetMapping("/map-viewsongs")
	public String viewSongs(Model model) {
		List<Songs> songslist = songserv.fetchAllSongs();
		model.addAttribute("listsongs", songslist);
		return "displaysongs";
	}
	
}
