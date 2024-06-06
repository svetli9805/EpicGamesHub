package com.epicgameshub.epic_games_hub.game;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("games")
@RequiredArgsConstructor
@Tag(name = "Game")
public class GameController {

    private final GameService service;

    @PostMapping
    public ResponseEntity<GameResponse> saveGame(
            @Valid @RequestBody GameRequest request,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping("/{game-id}")
    public ResponseEntity<GameResponse> findGameById(
            @PathVariable("game-id") Integer gameId
    ) {
        return ResponseEntity.ok(service.findById(gameId));
    }
}
