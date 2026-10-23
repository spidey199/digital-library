package com.digitallibrary.controller;

import org.springframework.web.bind.annotation.RestController;

import com.digitallibrary.entity.BorrowRecords;
import com.digitallibrary.service.BorrowedRecordService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class BorrowController {
    /*
     * 
     * POST /api/v1/borrow/{bookId}/user/{userId} Book borrow karna
     * POST /api/v1/borrow/return/{bookId}/user/{userId} Book return karna
     * GET /api/v1/borrow/history/{userId}
     */

    @Autowired 
    BorrowedRecordService brs;

    @PostMapping("/api/v1/borrow/{bookId}/user/{userId}")
    public ResponseEntity<BorrowRecords> borrowBook(@PathVariable("bookId") int bookID, @PathVariable ("userId") int userId) {
        BorrowRecords borrow = brs.borrowBook(userId, bookID);

        return ResponseEntity.status(HttpStatus.CREATED).body(borrow);
    }


    @PostMapping("/api/v1/borrow/return/{bookId}/user/{userId}")
    public ResponseEntity<BorrowRecords> returnBook(@PathVariable("bookId") int bookID, @PathVariable ("userId") int userId) {
        //TODO: process POST request
        BorrowRecords returnedBook = brs.returnBook(userId, bookID);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnedBook);
    }

    @GetMapping("/api/v1/borrow/history")
    public ResponseEntity<Page<BorrowRecords>> getHistory(   @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "2") int size) {
            org.springframework.data.domain.Pageable pageable = PageRequest.of(page, size);
        Page<BorrowRecords> borrowRecordsHistory = brs.getAllBorrowRecordHistory(pageable);
        return ResponseEntity.ok(borrowRecordsHistory);
    }
    
    

}
