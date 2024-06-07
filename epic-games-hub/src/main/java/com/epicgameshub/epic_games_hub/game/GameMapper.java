package com.epicgameshub.epic_games_hub.game;

import com.epicgameshub.epic_games_hub.file.FileUtils;
import com.epicgameshub.epic_games_hub.history.GameTransactionHistory;
import org.springframework.stereotype.Service;

@Service
public class GameMapper {
    public Game toGame(GameRequest request) {
        return Game.builder()
                .id(request.id())
                .title(request.title())
                .genre(request.genre())
                .platform(request.platform())
                .publisher(request.publisher())
                .releaseDate(request.releaseDate())
                .description(request.description())
                .shareable(request.sharable())
                .build();
    }

    public GameResponse toGameResponse(Game game) {
        return GameResponse.builder()
                .id(game.getId())
                .title(game.getTitle())
                .genre(game.getGenre())
                .platform(game.getPlatform())
                .publisher(game.getPublisher())
                .releaseDate(game.getReleaseDate())
                .rating(game.getRate())
                .description(game.getDescription())
                .sharable(game.isShareable())
                .coverImage(FileUtils.readFileFromLocation(game.getCoverImage()))
                .owner(game.getOwner().getFullName())
                .build();
    }

    public BorrowedGameResponse toBorrowedGameResponse(GameTransactionHistory gameTransactionHistory) {
        return BorrowedGameResponse.builder()
                .id(gameTransactionHistory.getId())
                .title(gameTransactionHistory.getGame().getTitle())
                .publisher(gameTransactionHistory.getGame().getPublisher())
                .rate(gameTransactionHistory.getGame().getRate())
                .returned(gameTransactionHistory.isReturned())
                .returnApproved(gameTransactionHistory.isReturnApproved())
                .build();
    }
}
