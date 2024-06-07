package com.epicgameshub.epic_games_hub.feedback;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedBackRepository extends JpaRepository<Feedback, Integer> {
    @Query("""
            SELECT feedback
            FROM Feedback  feedback
            WHERE feedback.game.id = :gameId
""")
    Page<Feedback> findAllByGameId(@Param("gameId") Integer gameId, Pageable pageable);
}
