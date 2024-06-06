package com.epicgameshub.epic_games_hub.game;

import com.epicgameshub.epic_games_hub.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static java.util.UUID.fromString;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public GameResponse save(GameRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Game game = gameMapper.fromRequest(user, request);
        return gameMapper.fromEntity(gameRepository.save(game));
    }

    public GameResponse findById(Integer gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("No game found with ID:: " + gameId));

        return gameMapper.fromEntity(game);
    }
}
