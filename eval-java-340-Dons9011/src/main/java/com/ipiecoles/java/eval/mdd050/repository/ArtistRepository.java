package com.ipiecoles.java.eval.mdd050.repository;

import com.ipiecoles.java.eval.mdd050.model.Artist;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Long> {
    Page<Artist> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Artist findByName(String name); 
    
}
