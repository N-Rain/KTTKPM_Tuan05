package com.example.kttkpm_8_3.repositories;

import com.example.kttkpm_8_3.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

