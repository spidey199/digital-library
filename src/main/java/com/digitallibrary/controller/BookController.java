package com.digitallibrary.controller;

import com.digitallibrary.repository.BookRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.digitallibrary.dto.BookReqDto;
import com.digitallibrary.dto.BookRespDto;
import com.digitallibrary.entity.Book;
import com.digitallibrary.service.BookService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping ("/api/v1/book")
public class BookController {
    

  
    @Autowired 
    BookService bookService;


    // BookController(BookRepository bookRepository) {
    //     this.bookRepository = bookRepository;
    // }

    
    //POST /books
    @PostMapping("/book")
    public BookRespDto addBook(@RequestBody BookReqDto bookReqDto){
        Book book = Book.builder()
                    .bookName(bookReqDto.getBookname())
                    .author(bookReqDto.getAuthor())
                    .publisher(bookReqDto.getPublisher())
                    .isbn(bookReqDto.getIsbn())
                    .category(bookReqDto.getCategory())
                    .description(bookReqDto.getDescription())
                    .build();
        
        Book addedBook = bookService.addBook(book);

        return BookRespDto.builder()
                        .bookname(addedBook.getBookName())
                        .author(addedBook.getAuthor())
                        .publisher(addedBook.getPublisher())
                        .isbn(addedBook.getIsbn())
                        .category(addedBook.getCategory())
                        .description(addedBook.getDescription())
                        .build();
    }



    //GET /books
    @GetMapping("/books")
    public Page<BookRespDto> getBook(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "bookName") String sortBy,
        @RequestParam(defaultValue = "asc") String direction){
            
            String sortProperty = switch (sortBy) {
                case "book_name" -> "bookName";
                case "book_id" -> "bookId";
                default -> sortBy;
            };
            Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortProperty).descending()
            : Sort.by(sortProperty).ascending();

            Pageable pageable = PageRequest.of(page, size, sort);

            Page<Book> booksPage = bookService.getAllBook(pageable); 

            return booksPage.map(book -> BookRespDto.builder()
            .bookname(book.getBookName())
            .author(book.getAuthor())
            .publisher(book.getPublisher())
            .copies(book.getCopies())
            .isbn(book.getIsbn())
            .category(book.getCategory())
            .description(book.getDescription())
            .build());
    }



    //GET /books/{id}
    @GetMapping ("/books/{id}")
    public Book getBookViaId(@RequestParam int id){
        return bookService.getBookById(id);
    }



    //PUT /books/{id}
    @PutMapping ("/books/:id")
    public Book updateBook(@Param("id") int id){
        return bookService.updateBook(bookService.getBookById(id));
    }



    // DELETE /books/{id}
    @DeleteExchange ("/books/{id}")
    public String deleteBook(@RequestParam int id){
        return bookService.deleteBook(bookService.getBookById(id));
    }


    // GET /books/category/{category}
    @GetMapping("/books/category/{category}")
    public List<Book> getBookViaCategory(@RequestParam String category) {
        return bookService.getBookByCategory(category);
    }
    
    
}
