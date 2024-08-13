package org.work.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.bookservice.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}