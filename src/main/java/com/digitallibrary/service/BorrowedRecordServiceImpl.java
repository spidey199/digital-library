package com.digitallibrary.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.digitallibrary.entity.Book;
import com.digitallibrary.entity.BorrowRecords;
import com.digitallibrary.entity.User;
import com.digitallibrary.exception.BookException;
import com.digitallibrary.exception.BorrowRecordException;
import com.digitallibrary.repository.BorrowedRecordRepository;

import jakarta.transaction.Transactional;

@Service 
public class BorrowedRecordServiceImpl implements BorrowedRecordService{

    @Autowired 
    UserService userService;
    @Autowired 
    BookService bookService;
    @Autowired 
    BorrowedRecordRepository brRepo;


    @Override
    @Transactional 
    public BorrowRecords borrowBook(int userid, int bookid) {
        // TODO Auto-generated method stub
        // - `UserRepository` se user find karo.
        // - `BookRepository` se book find karo.
        // - Check karo ki `availableCopies > 0` hai.
        // - `BorrowedHistory` record create karo.
        // - Available copies ko ek se decrement karo.
        // - Dono database changes ko `@Transactional` mein rakho.
    
        User user = userService.getUserById(userid);
        Book book = bookService.getBookById(bookid);

        int copy = book.getCopies();
        if(copy>0){
            BorrowRecords br = BorrowRecords.builder()
                            .user(user)
                            .book(book)
                            .borrowDate(LocalDate.now())
                            .returnDate(null)
                            .build();

            book.setCopies(copy-1);
            // bookService.updateBook(book);


            // // TEMPORARY: rollback test only
            // if (Boolean.TRUE) {
            //     throw new RuntimeException("TEST ROLLBACK");
            // }

            return brRepo.save(br);
        }
        
        throw new BorrowRecordException("Borrowing failed!!");
    
    }


    @Override
    @Transactional 
    public BorrowRecords returnBook(int userid, int bookid) {
        // TODO Auto-generated method stub
        User user = userService.getUserById(userid);
        Book book = bookService.getBookById(bookid);
   

         BorrowRecords record = brRepo
                                    .findActiveByUserIdAndBookId(
                                                                            userid, bookid)
                                    .orElseThrow(() ->
                                                new BorrowRecordException(
                                                "No active borrowing found for this user and book"));
        record.setReturnDate(LocalDate.now());
        
        book.setCopies(book.getCopies()+1);
        // bookService.updateBook(book);
        return brRepo.save(record);

    
    }


    @Override
    public Page<BorrowRecords> getAllBorrowRecordHistory(Pageable pageable) {
       return brRepo.findAll( pageable);
    }

    
    
}
