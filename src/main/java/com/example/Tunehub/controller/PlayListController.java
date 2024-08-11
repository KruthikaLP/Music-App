package com.example.Tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Tunehub.entities.PlayList;
import com.example.Tunehub.entities.Songs;
import com.example.Tunehub.service.PlayListService;
import com.example.Tunehub.service.SongService;

@CrossOrigin("*")
@Controller
public class PlayListController {
	
	@Autowired
	PlayListService pserv;
	
	@Autowired
	SongService sserv;

	
	@GetMapping("/createplaylist")
	public String createPlayList(Model model) {
		
		//Fetching the songs using song service
		List<Songs> songslist=sserv.fetchAllSongs();
		
		//Adding the songs in the model
		model.addAttribute("songsslist",songslist);
		
		//sending createplaylist
		return "createplaylist";
	}
	
	
	@PostMapping("/addplaylist")
	public String addPlayList(@ModelAttribute PlayList playlist) {
		pserv.addPlayList(playlist);
		
		List<Songs> songList = playlist.getSongs();
		for(Songs song : songList) {
			song.getPlaylist().add(playlist);
			sserv.updateSong(song);
		}
		
		return "PlayListSucess";
		
	}
	
	@GetMapping("/viewplaylist")
	public String viewPlaylist(Model model) {
		List<PlayList> plist = pserv.fetchPlayList();
		model.addAttribute("plist", plist);
		return "viewplaylist";
	}

}
