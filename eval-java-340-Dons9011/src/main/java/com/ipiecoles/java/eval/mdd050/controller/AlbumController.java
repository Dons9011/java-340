package com.ipiecoles.java.eval.mdd050.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ipiecoles.java.eval.mdd050.model.Album;
import com.ipiecoles.java.eval.mdd050.model.Artist;
import com.ipiecoles.java.eval.mdd050.repository.AlbumRepository;
import com.ipiecoles.java.eval.mdd050.repository.ArtistRepository;
import com.ipiecoles.java.eval.mdd050.service.AlbumService;
import com.ipiecoles.java.eval.mdd050.service.ArtistService;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {
	
	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private ArtistService artistService;
	
	@Autowired 
	private AlbumService albumService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Album ajouteAlbumToArtist(@RequestBody Album album) {
		return albumService.creerAlbum(album);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deleteAlbum(
			@PathVariable ("id") Long id) {
		albumService.deleteAlbum(id);
	}
	
}
