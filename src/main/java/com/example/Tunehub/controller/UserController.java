package com.example.Tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Tunehub.entities.Songs;
import com.example.Tunehub.entities.Users;
import com.example.Tunehub.service.SongService;
import com.example.Tunehub.service.UserService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@Controller
//@RestController

public class UserController {

	@Autowired
	UserService userser;

	@Autowired
	SongService songserv;

	@PostMapping("/map-studentform")
	public String addUser(@ModelAttribute Users user) {
		
		if(userser.findByEmail(user.getEmail())==false){
			userser.addUser(user);
			return "registrationsucessfull";
		}
		else {
			return "registrationfail";
		}
	}

	@PostMapping("/map-loginform")
	public String findEmail(@RequestParam String email,@RequestParam String password,HttpSession session ) {
		
		Users user = userser.getUser(email);
		if(user!=null) {
			if(userser.findPassword(email, password)==true) {

				session.setAttribute("email", email);
				if(userser.findRole(email).equals("admin")) {
					return "adminhome";
				}
				else {
					return "customerhome";
				}
			}
			else {
				return "loginfail";
			}
		}
		else {
			return "fail";
		}

	}

	@GetMapping("/exploresongs")
	public String exploreSongs(HttpSession session,Model model ) {
		
		    String email = (String) session.getAttribute("email");
		
			Users user = userser.getUser(email);
			
		

				if(user.isPremium()==true) {
					List<Songs> songslist = songserv.fetchAllSongs();
					model.addAttribute("listsongs", songslist);
					return "displaysongs";
				}
				else {
					return "payment";	
				}
			
			
	}
	
}

//loginsucessfull