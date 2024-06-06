package com.epicgameshub.epic_games_hub.game;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.parameters.P;

@Data
public class GameRequest {
    private Integer id;
    @NotNull(message = "100")
    @NotEmpty(message = "100")
    private String title;
    private String genre;
    private String platform;
    @NotNull(message = "101")
    @NotEmpty(message = "101")
    private String publisher;
    private String releaseDate;
    private String description;
    private boolean sharable;
}