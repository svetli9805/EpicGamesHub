package com.epicgameshub.epic_games_hub.game;

import com.epicgameshub.epic_games_hub.config.Config;
import com.epicgameshub.epic_games_hub.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = Config.class)
public interface GameMapper {

    Game fromRequest(User user, GameRequest request);
    GameResponse fromEntity(Game game);
}
