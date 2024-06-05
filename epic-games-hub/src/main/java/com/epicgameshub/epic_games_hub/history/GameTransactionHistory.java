package com.epicgameshub.epic_games_hub.history;

import com.epicgameshub.epic_games_hub.common.BaseEntity;
import com.epicgameshub.epic_games_hub.game.Game;
import com.epicgameshub.epic_games_hub.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GameTransactionHistory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private boolean returned;
    private boolean returnApproved;
}
