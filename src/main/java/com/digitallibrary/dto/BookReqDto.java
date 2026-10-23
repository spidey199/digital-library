package com.digitallibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * BookReqDto
 */
@Getter
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
@ToString 
public class BookReqDto {
    @NotBlank(message =  "Book name can not be blank....") 
    @JsonProperty ("bookname")
    private String bookname;
    @NotBlank(message =  "Book author can not be blank....")
    private String author;
    @NotBlank(message =  "Book publisher can not be blank....")
    private String publisher;
    private int copies;
    @NotBlank(message =  "Book ISBN can not be blank....")
    private String isbn;
    @NotBlank(message =  "Book category can not be blank....")
    private String category;
    private String description;
}
