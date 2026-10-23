package com.digitallibrary;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.context.annotation.Bean;

import com.digitallibrary.entity.Book;
import com.digitallibrary.entity.BorrowRecords;
import com.digitallibrary.entity.User;
import com.digitallibrary.repository.BookRepository;
import com.digitallibrary.repository.BorrowedRecordRepository;
import com.digitallibrary.repository.UserRepository;
import com.digitallibrary.service.BookService;
import com.digitallibrary.service.BorrowedRecordService;

@SpringBootApplication
public class DigitallibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitallibraryApplication.class, args);
	}


@Bean
CommandLineRunner testBorrowRollback(
        BorrowedRecordService borrowedRecordService,
        BookService bookService) {

    return args -> {

        // int userId = 1; // Change to an existing user ID
        // int bookId = 1; // Change to an existing book ID

        // Book before = bookService.getBookById(bookId);
        // int originalCopies = before.getCopies();

        // if (originalCopies <= 0) {
        //     System.out.println(
        //             "TEST SKIPPED: No copies available");
        //     return;
        // }

        // try {
        //     borrowedRecordService.borrowBook(userId, bookId);
        // } catch (RuntimeException ex) {
        //     System.out.println(
        //             "Expected failure: " + ex.getMessage());
        // }

        // Book after = bookService.getBookById(bookId);

        // System.out.println(
        //         "Copies before: " + originalCopies);

        // System.out.println(
        //         "Copies after: " + after.getCopies());

        // System.out.println(
        //         originalCopies == after.getCopies()
        //                 ? "ROLLBACK TEST PASSED"
        //                 : "ROLLBACK TEST FAILED");



        
        //bookService.getBookByCategory("Programming").forEach((b) -> System.out.println(b.toString()));
    };
}

}
