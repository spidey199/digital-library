package com.digitallibrary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digitallibrary.entity.BorrowRecords;

public interface BorrowedRecordRepository extends JpaRepository<BorrowRecords, Integer>{

    @Query("select br from BorrowRecords br where br.user.id = :userId and br.book.bookId = :bookId")
    BorrowRecords findByUseridAndBookid(@Param("userId") int userId, @Param("bookId") int bookId);

    @Query("select br from BorrowRecords br where br.user.id = :userId and br.book.bookId = :bookId and br.returnDate is null")
    Optional<BorrowRecords> findActiveByUserIdAndBookId(
        @Param("userId") int userId, @Param("bookId") int bookId);
}
