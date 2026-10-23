package com.digitallibrary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.digitallibrary.entity.Book;
import com.digitallibrary.entity.BorrowRecords;
import com.digitallibrary.entity.User;

@Service 
public interface BorrowedRecordService {
    public BorrowRecords borrowBook(int userid, int bookid);
    public BorrowRecords returnBook(int userid, int bookid);

    public Page<BorrowRecords> getAllBorrowRecordHistory(Pageable pageable);
}
