package com.ipiecoles.java.eval.mdd050.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ipiecoles.java.eval.mdd050.model.Artist;
import com.ipiecoles.java.eval.mdd050.repository.AlbumRepository;
import com.ipiecoles.java.eval.mdd050.repository.ArtistRepository;
import com.ipiecoles.java.eval.mdd050.service.AlbumService;
import com.ipiecoles.java.eval.mdd050.service.ArtistService;

@RestController
@RequestMapping(value = "/artists")
public class ArtisteController {

	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private ArtistService artistService;
	
	@Autowired 
	private AlbumService albumService; 

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Artist getArtiste(@PathVariable(value = "id") Long id) {
		
		return artistService.findById(id);
	}

	@RequestMapping(value = "", params = "name", method = RequestMethod.GET)
	public Page<Artist> findArtists(@RequestParam("name") String name, @RequestParam("page") Integer page, @RequestParam("size") Integer size,
			@RequestParam("sortProperty") String sortProperty, @RequestParam("sortDirection") String sortDirection) {

		return artistService.findByNameLikeIgnoreCase(name, page, size, sortProperty, sortDirection);
	}

	@RequestMapping(value = "", method = RequestMethod.GET )
	public Page<Artist> findAllArtists(@RequestParam("page") Integer page, @RequestParam("size") Integer size,
			@RequestParam("sortProperty") String sortProperty, @RequestParam("sortDirection") String sortDirection) {
		 return artistService.findAllArtists(page, size, sortProperty, sortDirection);
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Artist createArtist(@RequestBody Artist artist) {
//		if (artist.getName()) {
//			throws new MethodArgumentTypeMismatchException();
//		}
		if(artistRepository.findByName(artist.getName()) != null) {
			throw new EntityExistsException("L'artiste " + artist.getName() +" existe déjà !");
		}
		return artistService.creerArtiste(artist);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Artist updateArtist(
			@RequestBody Artist artist,
			@PathVariable ("id") Long id) {
		return artistService.updateArtiste(id, artist);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deleteArtist(
			@PathVariable ("id") Long id) {
		
		artistService.deleteArtist(id);
		albumService.deleteAlbum(id);
	}
	

}
