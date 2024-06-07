package com.epicgameshub.epic_games_hub.feedback;


import com.epicgameshub.epic_games_hub.game.Game;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeedbackMapper {
    public Feedback toFeedback(FeedBackRequest request) {
        return Feedback.builder()
                .note(request.note())
                .comment(request.comment())
                .game(Game.builder()
                        .id(request.gameId())
                        .shareable(false)
                        .build()
                )
                .build();
    }

    public FeedBackResponse toFeedbackResponse(Feedback feedback, Integer id) {
        return FeedBackResponse.builder()
                .note(feedback.getNote())
                .comment(feedback.getComment())
                .ownFeedback(Objects.equals(feedback.getCreatedBy(), id))
                .build();
    }
}
