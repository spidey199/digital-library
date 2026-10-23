package com.digitallibrary.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digitallibrary.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

   @Query("select b from Book b where b.bookName = :bookName")
   Book findByBookName(@Param("bookName") String bookName);

   List<Book> findByCategory(String category);

   Page<Book> findAll(Pageable pageable);
}
