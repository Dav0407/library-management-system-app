package com.igriss.library_manager.feedback;

import com.igriss.library_manager.book.Book;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeedbackMapper {
    public Feedback toFeedback(FeedbackRequest request) {
        return Feedback.builder()
                .note(request.note())
                .comment(request.comment())
                .book(
                        Book.builder()
                                .id(request.bookId())
                                .archived(false) // Value is not required and has no impact:: just to satisfy lombok
                                .shareable(false) // Value is not required and has no impact:: just to satisfy lombok
                                .build()
                )
                .build();
    }

    public FeedbackResponse toFeedbackResponse(Feedback feedback, Long userId) {
        return FeedbackResponse.builder()
                .note(feedback.getNote())
                .comment(feedback.getComment())
                .ownFeedback(Objects.equals(feedback.getCreatedBy(), userId))
                .build();
    }
}
