package com.epicgameshub.epic_games_hub.game;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


public interface GameRepository extends JpaRepository<Game, Integer>, JpaSpecificationExecutor<Game> {

    @Query("""
            SELECT game
            FROM Game game
            WHERE game.shareable = true
            AND game.owner.id != :userId
            """)
    Page<Game> findAllDisplayableGames(Pageable pageable, Integer userId);
}