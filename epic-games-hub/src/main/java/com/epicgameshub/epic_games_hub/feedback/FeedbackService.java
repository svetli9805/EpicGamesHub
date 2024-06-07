package com.epicgameshub.epic_games_hub.feedback;

import com.epicgameshub.epic_games_hub.common.PageResponse;
import com.epicgameshub.epic_games_hub.exception.OperationNotPermittedException;
import com.epicgameshub.epic_games_hub.game.Game;
import com.epicgameshub.epic_games_hub.game.GameRepository;
import com.epicgameshub.epic_games_hub.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedBackRepository feedBackRepository;
    private final GameRepository gameRepository;
    private final FeedbackMapper feedbackMapper;

    public Integer save(FeedBackRequest request, Authentication connectedUser) {
        Game game = gameRepository.findById(request.gameId())
                .orElseThrow(() -> new EntityNotFoundException("No game found with ID:: " + request.gameId()));
        if (!game.isShareable()) {
            throw new OperationNotPermittedException("You cannot give a feedback for not shareable book");
        }
        User user = ((User) connectedUser.getPrincipal());
        if (Objects.equals(game.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot give feedback to your own game");
        }
        Feedback feedback = feedbackMapper.toFeedback(request);
        return feedBackRepository.save(feedback).getId();
    }

    @Transactional(readOnly = true)
    public PageResponse<FeedBackResponse> findAllFeedbacksByBook(Integer bookId, int page, int size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size);
        User user = ((User) connectedUser.getPrincipal());
        Page<Feedback> feedbacks = feedBackRepository.findAllByGameId(bookId, pageable);
        List<FeedBackResponse> feedbackResponses = feedbacks.stream()
                .map(f -> feedbackMapper.toFeedbackResponse(f, user.getId()))
                .toList();
        return new PageResponse<>(
                feedbackResponses,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast()
        );
    }
}
