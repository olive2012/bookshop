package it.akademija.books.service;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class BookServiceObject {
    @NotNull
    @Length(min = 1, max = 200)
    private String isbn;

    @NotNull
    @Length(min = 1, max = 200)
    private String author;

    @NotNull
    @Length(min = 1, max = 200)
    private String title;

    @NotNull
    @Min(1)
    private int numOfPages;

    public BookServiceObject()
    {

    }

    public BookServiceObject(String isbn, String author, String title, int numOfPages) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.numOfPages = numOfPages;
    }


}
