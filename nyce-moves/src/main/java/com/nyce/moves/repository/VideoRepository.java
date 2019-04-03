package com.nyce.moves.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nyce.moves.model.Image;
import com.nyce.moves.model.Video;

@Repository
public interface VideoRepository extends CrudRepository<Video, Long> {
	
	// The implementation of this method will be done automatically by the Spring JPA. No need to implement this method.
	// Only thing which needs to be taken care of is use findBy property-name
	public List<Video> findByPlayerId(Long postedBy);
	
	public List<Video> fetchVideosByPlayerIdList(@Param("inclList")List<Long> playerId);
	
}
