package com.epicgameshub.epic_games_hub.game;

import com.epicgameshub.epic_games_hub.common.PageResponse;
import com.epicgameshub.epic_games_hub.exception.OperationNotPermittedException;
import com.epicgameshub.epic_games_hub.file.FileStorageService;
import com.epicgameshub.epic_games_hub.history.GameTransactionHistory;
import com.epicgameshub.epic_games_hub.history.GameTransactionHistoryRepository;
import com.epicgameshub.epic_games_hub.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

import static com.epicgameshub.epic_games_hub.game.GameSpecification.withOwnerId;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {
    private final GameRepository gameRepository;
    private final GameTransactionHistoryRepository transactionHistoryRepository;
    private final FileStorageService fileStorageService;
    private final GameMapper gameMapper;

    @Transactional
    public GameResponse save(GameRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Game game = gameMapper.toGame(request);
        game.setOwner(user);
        return gameMapper.toGameResponse(gameRepository.save(game));
    }

    @Transactional(readOnly = true)
    public GameResponse findById(Integer gameId) {
        return gameRepository.findById(gameId)
                .map(gameMapper::toGameResponse)
                .orElseThrow(() -> new EntityNotFoundException("No game found with ID:: " + gameId));
    }

    @Transactional(readOnly = true)
    public PageResponse<GameResponse> findAllGames(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Game> games = gameRepository.findAllDisplayableGames(pageable, user.getId());
        List<GameResponse> booksResponse = games.stream()
                .map(gameMapper::toGameResponse)
                .toList();
        return new PageResponse<>(
                booksResponse,
                games.getNumber(),
                games.getSize(),
                games.getTotalElements(),
                games.getTotalPages(),
                games.isFirst(),
                games.isLast()
        );
    }

    public PageResponse<GameResponse> findAllGamesByOwner(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Game> games = gameRepository.findAll(withOwnerId(user.getId()), pageable);
        List<GameResponse> gameResponses = games.stream()
                .map(gameMapper::toGameResponse)
                .toList();
        return new PageResponse<>(
                gameResponses,
                games.getNumber(),
                games.getSize(),
                games.getTotalElements(),
                games.getTotalPages(),
                games.isFirst(),
                games.isLast()
        );
    }

    public PageResponse<BorrowedGameResponse> findAllBorrowedGames(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<GameTransactionHistory> allBorrowedBooks = transactionHistoryRepository.findAllBorrowedGames(pageable, user.getId());
        List<BorrowedGameResponse> booksResponse = allBorrowedBooks.stream()
                .map(gameMapper::toBorrowedGameResponse)
                .toList();
        return new PageResponse<>(
                booksResponse,
                allBorrowedBooks.getNumber(),
                allBorrowedBooks.getSize(),
                allBorrowedBooks.getTotalElements(),
                allBorrowedBooks.getTotalPages(),
                allBorrowedBooks.isFirst(),
                allBorrowedBooks.isLast()
        );
    }

    public PageResponse<BorrowedGameResponse> findAllReturnedGames(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<GameTransactionHistory> allBorrowedBooks = transactionHistoryRepository.findAllReturnedGames(pageable, user.getId());
        List<BorrowedGameResponse> booksResponse = allBorrowedBooks.stream()
                .map(gameMapper::toBorrowedGameResponse)
                .toList();
        return new PageResponse<>(
                booksResponse,
                allBorrowedBooks.getNumber(),
                allBorrowedBooks.getSize(),
                allBorrowedBooks.getTotalElements(),
                allBorrowedBooks.getTotalPages(),
                allBorrowedBooks.isFirst(),
                allBorrowedBooks.isLast()
        );
    }

    public Integer updateShareableStatus(Integer gameId, Authentication connectedUser) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("No game found with ID:: " + gameId));
        User user = ((User) connectedUser.getPrincipal());
        if (!Objects.equals(game.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot update others games shareable status");
        }
        game.setShareable(!game.isShareable());
        gameRepository.save(game);
        return gameId;
    }

    public Integer borrowGame(Integer gameId, Authentication connectedUser) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("No game found with ID:: " + gameId));
        if (!game.isShareable()) {
            throw new OperationNotPermittedException("The requested game cannot be borrowed since it is not shareable");
        }
        User user = ((User) connectedUser.getPrincipal());
        if (Objects.equals(game.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot borrow your own game");
        }
        final boolean isAlreadyBorrowedByUser = transactionHistoryRepository.isAlreadyBorrowedByUser(gameId, user.getId());
        if (isAlreadyBorrowedByUser) {
            throw new OperationNotPermittedException("You already borrowed this game and it is still not returned or the return is not approved by the owner");
        }

        final boolean isAlreadyBorrowedByOtherUser = transactionHistoryRepository.isAlreadyBorrowed(gameId);
        if (isAlreadyBorrowedByOtherUser) {
            throw new OperationNotPermittedException("Te requested book is already borrowed");
        }

        GameTransactionHistory gameTransactionHistory = GameTransactionHistory.builder()
                .user(user)
                .game(game)
                .returned(false)
                .returnApproved(false)
                .build();
        return transactionHistoryRepository.save(gameTransactionHistory).getId();
    }

    public Integer returnBorrowedGame(Integer gameId, Authentication connectedUser) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("No game found with ID:: " + gameId));
        if (!game.isShareable()) {
            throw new OperationNotPermittedException("The requested book is not shareable");
        }
        User user = ((User) connectedUser.getPrincipal());
        if (Objects.equals(game.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot borrow or return your own game");
        }

        GameTransactionHistory gameTransactionHistory = transactionHistoryRepository.findByGameIdAndUserId(gameId, user.getId())
                .orElseThrow(() -> new OperationNotPermittedException("You did not borrow this game"));

        gameTransactionHistory.setReturned(true);
        return transactionHistoryRepository.save(gameTransactionHistory).getId();
    }

    public Integer approveReturnBorrowedGame(Integer gameId, Authentication connectedUser) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("No game found with ID:: " + gameId));
        if (!game.isShareable()) {
            throw new OperationNotPermittedException("The requested game is not shareable");
        }
        User user = ((User) connectedUser.getPrincipal());
        if (!Objects.equals(game.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot approve the return of a game you do not own");
        }

        GameTransactionHistory gameTransactionHistory = transactionHistoryRepository.findByGameIdAndOwnerId(gameId, user.getId())
                .orElseThrow(() -> new OperationNotPermittedException("The game is not returned yet. You cannot approve its return"));

        gameTransactionHistory.setReturnApproved(true);
        return transactionHistoryRepository.save(gameTransactionHistory).getId();
    }

    public void uploadGameImageCover(MultipartFile file, Authentication connectedUser, Integer gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("No game found with ID:: " + gameId));
        User user = ((User) connectedUser.getPrincipal());
        var profilePicture = fileStorageService.saveFile(file, gameId, user.getId());
        game.setCoverImage(profilePicture);
        gameRepository.save(game);
    }
}
