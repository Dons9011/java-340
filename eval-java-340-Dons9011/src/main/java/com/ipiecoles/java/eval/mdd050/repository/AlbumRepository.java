package com.ipiecoles.java.eval.mdd050.repository;

import com.ipiecoles.java.eval.mdd050.model.Album;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {
}
