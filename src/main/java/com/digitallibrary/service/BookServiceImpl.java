package com.digitallibrary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.digitallibrary.entity.Book;
import com.digitallibrary.exception.BookException;
import com.digitallibrary.repository.BookRepository;

@Service 
public class BookServiceImpl implements BookService {

    @Autowired 
    BookRepository bookRepo;

    @Override
    public Book addBook(Book book) {
        // TODO Auto-generated method stub
       if (book == null) {
        throw new BookException("Book cannot be null");

    }

        if (bookRepo.findByBookName(book.getBookName()) != null) {
             return updateBookCopies(book, true);
            //throw new BookException("Book already exists");
        }

        return bookRepo.save(book);
    }

    private Book findByBookName(String bookName) {
        // TODO Auto-generated method stub
        Book book = bookRepo.findByBookName(bookName);
        if(book == null){
            throw new BookException("No book found with this name..");
        }
        return book;
        
    }

    @Override
    public List<Book> getAllBook() {
        // TODO Auto-generated method stub
        List<Book> books = bookRepo.findAll();
        return books;
    }

    public Page<Book> getAllBook(Pageable pageable){
        return bookRepo.findAll(pageable);
    }

    @Override
    public Book getBookById(int bookid) {
        // TODO Auto-generated method stub
        return bookRepo.findById(bookid)
            .orElseThrow(() ->
                    new BookException(
                        "Book not found with id: " + bookid
                    )
            );
        
    }

    @Override
    public Book updateBook(Book book) {
        // TODO Auto-generated method stub
        if (book == null) {
            throw new BookException("Book cannot be null");
        }

        Book existingBook = bookRepo.findById(book.getBookId())
                .orElseThrow(() -> new BookException(
                "Book not found with id: " + book.getBookId()));

        existingBook.setBookName(book.getBookName());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setCopies(book.getCopies());
        existingBook.setDescription(book.getDescription());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setPublisher(book.getPublisher());

        
        return bookRepo.save(existingBook);
    }

    @Override 
    public Book updateBookCopies(Book book, boolean increase) {
        // TODO Auto-generated method stub
        
        if(increase){
            book.setCopies(book.getCopies()+1);
        }else{
            book.setCopies(book.getCopies()-1);
        }

    
        return  updateBook(book);

    }

    @Override
    public String deleteBook(Book book) {
        // TODO Auto-generated method stub
        bookRepo.delete(book);
        return "Deletion for the book is successfull!!..";
    }

    @Override
    public List<Book> getBookByCategory(String category) {
        // TODO Auto-generated method stub
        if(category == null){
             throw new RuntimeException("Category name is null in service method..!!");
        }
        List<Book> booksInCategory = bookRepo.findByCategory( category);
        return booksInCategory;
    }

    
    
}
