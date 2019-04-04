package com.nyce.moves.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nyce.moves.model.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
	
	// The implementation of this method will be done automatically by the Spring JPA. No need to implement this method.
	// Only thing which needs to be taken care of is use findBy property-name
	public List<Player> findByEmail(String email);
	
	public List<Player> searchPlayersByString(@Param("searchString1")String searchString1, @Param("searchString2")String searchString2, @Param("searchString3")String searchString3, @Param("playerId")Long playerId);
	
}
