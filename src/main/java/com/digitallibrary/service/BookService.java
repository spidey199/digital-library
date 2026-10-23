package com.digitallibrary.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.digitallibrary.entity.Book;

public interface BookService {
    //addBook() — admin ke liye book add karna.
    public Book addBook(Book book);

    public Page<Book> getAllBook(Pageable pageable);


    //getAllBooks() — saari books fetch karna.
    public List<Book> getAllBook();


    //getBookById() — ID se book find karna.
    public Book getBookById(int bookid);



    //updateBook() — book update karna.
    public Book updateBook(Book book);

    //update copies of book
    public Book updateBookCopies(Book book, boolean increase);



    //deleteBook() — book remove/deactivate karna.
    public String deleteBook(Book book);

    //get all books under same catgory
    public List<Book> getBookByCategory(String category);
}
