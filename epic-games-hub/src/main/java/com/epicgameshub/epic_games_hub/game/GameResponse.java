package com.epicgameshub.epic_games_hub.game;

import com.epicgameshub.epic_games_hub.feedback.Feedback;
import com.epicgameshub.epic_games_hub.history.GameTransactionHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameResponse {
    private Integer id;
    private String title;
    private String genre;
    private String platform;
    private String publisher;
    private String releaseDate;
    private String description;
    private double rating;
    private boolean availability;
    private boolean sharable;
    private byte[] coverImage;
    private String owner;
    private List<Feedback> feedbacks;
    private List<GameTransactionHistory> histories;
}
