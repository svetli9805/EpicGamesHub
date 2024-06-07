package com.epicgameshub.epic_games_hub.game;

import com.epicgameshub.epic_games_hub.common.BaseEntity;
import com.epicgameshub.epic_games_hub.feedback.Feedback;
import com.epicgameshub.epic_games_hub.history.GameTransactionHistory;
import com.epicgameshub.epic_games_hub.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
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
    private String coverImage;
    private int rating;
    private boolean availability;
    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "game")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "game")
    private List<GameTransactionHistory> histories;

    @Transient
    public double getRate() {
        if (feedbacks == null || feedbacks.isEmpty()) {
            return 0.0;
        }
        var rate = this.feedbacks.stream()
                .mapToDouble(Feedback::getNote)
                .average()
                .orElse(0.0);
        return Math.round(rate * 10.0) / 10.0;
    }
}
