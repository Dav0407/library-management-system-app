package com.igriss.library_manager.feedback;

import com.igriss.library_manager.book.Book;
import com.igriss.library_manager.book.BookRepository;
import com.igriss.library_manager.common.PageResponse;
import com.igriss.library_manager.exception.OperationNotPermittedException;
import com.igriss.library_manager.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final BookRepository bookRepository;
    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;

    public Long save(FeedbackRequest request, Authentication connectedUser) {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("No book found with the ID:: " + request.bookId()));
        if (book.isArchived() || !book.isShareable()){
            throw new OperationNotPermittedException("You can not give a feedback for an archived or not shareable book.");
        }
        User user = (User) connectedUser.getPrincipal();
        if (Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You can not give a feedback to your own book.");
        }
        Feedback feedback = feedbackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();
    }

    public PageResponse<FeedbackResponse> findAllFeedbacksByBook(Long bookId, int page, int size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size);
        User user = (User) connectedUser.getPrincipal();
        Page<Feedback> feedbacks = feedbackRepository.findAllFeedbacksByBook(bookId, pageable);
        List<FeedbackResponse> feedbackResponses = feedbacks.stream()
                .map(feedback -> feedbackMapper.toFeedbackResponse(feedback, user.getId()))
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
