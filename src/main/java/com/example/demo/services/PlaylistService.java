package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Playlist;

public interface PlaylistService {
	

	public List<Playlist> fetchAllPlayLists();

	

	public void addPlaylist(Playlist playlist);
}
