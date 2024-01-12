package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Song;
import com.example.demo.services.SongService;

@Controller
public class SongController {
	
	@Autowired
	SongService service;
	
	
	public SongController(SongService service) {
		super();
		this.service = service;
	}
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song) {
		boolean songStatus = service.songExitst(song.getName());
		if(songStatus==false) {
			service.addSong(song);
			System.out.println("song added sucessfully");
		}else {
			System.out.println("Song already exists");
		}
		return "adminHome";
	}
	@GetMapping("/viewSongs")
	public String viewSongs(Model model) {
		List<Song> songList=service.fetchAllSongs();
		model.addAttribute("songs",songList);
		return "displaySong";
	}
	@GetMapping("/playsongs")
	public String playsongs(Model model) {
		boolean premiumUser=false;
		if(premiumUser == true) {
			List<Song> songList=service.fetchAllSongs();
			model.addAttribute("songs",songList);
			return "displaySong";
		}else {
			return "makePayment";
		}
	}
		
}