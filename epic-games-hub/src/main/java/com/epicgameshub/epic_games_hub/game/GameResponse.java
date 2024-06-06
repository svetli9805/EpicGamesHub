package com.epicgameshub.epic_games_hub.game;

import com.epicgameshub.epic_games_hub.feedback.Feedback;
import com.epicgameshub.epic_games_hub.history.GameTransactionHistory;
import lombok.Data;

import java.util.List;

@Data
public class GameResponse {
    private Long id;
    private String title;
    private String genre;
    private String platform;
    private String publisher;
    private String releaseDate;
    private String description;
    private int rating;
    private boolean availability;
    private boolean sharable;
    private String owner;
    private List<Feedback> feedbacks;
    private List<GameTransactionHistory> histories;
}
