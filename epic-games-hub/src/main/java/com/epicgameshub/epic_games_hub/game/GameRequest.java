package com.epicgameshub.epic_games_hub.game;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record GameRequest(
        Integer id,
        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String title,
        String genre,
        String platform,
        @NotNull(message = "101")
        @NotEmpty(message = "101")
        String publisher,
        String releaseDate,
        String description,
        boolean sharable
) {}