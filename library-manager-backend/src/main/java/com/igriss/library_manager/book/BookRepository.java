package com.igriss.library_manager.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Query("""
    SELECT book
    FROM Book book
    WHERE book.archived = false
    AND book.shareable = true
    AND book.owner.id != :userId
    """)
    Page<Book> findAllDisplayableBooks(Pageable pageable, Long userId);
}