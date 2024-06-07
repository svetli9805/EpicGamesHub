package com.epicgameshub.epic_games_hub.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GameTransactionHistoryRepository extends JpaRepository<GameTransactionHistory, Integer> {
    @Query("""
            SELECT history
            FROM GameTransactionHistory history
            WHERE history.user.id = :userId
            """)
    Page<GameTransactionHistory> findAllBorrowedGames(Pageable pageable, Integer userId);

    @Query("""
            SELECT history
            FROM GameTransactionHistory history
            WHERE history.game.owner.id = :userId
            """)
    Page<GameTransactionHistory> findAllReturnedGames(Pageable pageable, Integer userId);

    @Query("""
            SELECT
            (COUNT (*) > 0) AS isBorrowed
            FROM GameTransactionHistory gameTransactionHistory
            WHERE gameTransactionHistory.user.id = :userId
            AND gameTransactionHistory.game.id = :gameId
            AND gameTransactionHistory.returnApproved = false
            """)
    boolean isAlreadyBorrowedByUser(@Param("gameId") Integer gameId, @Param("userId") Integer userId);

    @Query("""
            SELECT
            (COUNT (*) > 0) AS isBorrowed
            FROM GameTransactionHistory gameTransactionHistory
            WHERE gameTransactionHistory.game.id = :gameId
            AND gameTransactionHistory.returnApproved = false
            """)
    boolean isAlreadyBorrowed(@Param("gameId") Integer gameId);

    @Query("""
            SELECT transaction
            FROM GameTransactionHistory  transaction
            WHERE transaction.user.id = :userId
            AND transaction.game.id = :gameId
            AND transaction.returned = false
            AND transaction.returnApproved = false
            """)
    Optional<GameTransactionHistory> findByGameIdAndUserId(@Param("gameId") Integer gameId, @Param("userId") Integer userId);

    @Query("""
            SELECT transaction
            FROM GameTransactionHistory  transaction
            WHERE transaction.game.owner.id = :userId
            AND transaction.game.id = :gameId
            AND transaction.returned = true
            AND transaction.returnApproved = false
            """)
    Optional<GameTransactionHistory> findByGameIdAndOwnerId(@Param("gameId") Integer gameId, @Param("userId") Integer userId);

}
