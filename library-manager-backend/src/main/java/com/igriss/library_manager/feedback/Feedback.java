package com.igriss.library_manager.feedback;

import com.igriss.library_manager.book.Book;
import com.igriss.library_manager.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback extends BaseEntity {

    private Double note;  // rating
    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}
