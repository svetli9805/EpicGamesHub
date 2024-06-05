package com.epicgameshub.epic_games_hub.game;

import com.epicgameshub.epic_games_hub.common.BaseEntity;
import com.epicgameshub.epic_games_hub.feedback.Feedback;
import com.epicgameshub.epic_games_hub.history.GameTransactionHistory;
import com.epicgameshub.epic_games_hub.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game extends BaseEntity {
    private String title;
    private String genre;
    private String platform;
    private String publisher;
    private String releaseDate;
    private String description;
    private int rating;
    private boolean availability;
    private boolean sharable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "game")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "game")
    private List<GameTransactionHistory> histories;
}
