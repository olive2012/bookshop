package it.akademija.books.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.akademija.books.service.BookService;
import it.akademija.books.service.BookServiceObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "book")
@RequestMapping("/api/books")
public class BookController {

    private BookService service;

    @Autowired
    public void setService(BookService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get books", notes = "Returns all known books")
    public List<BookServiceObject> getAll() {
        return service.getAllBooks();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a book", notes = "Creates a new book")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@ApiParam(value = "New book data", required = true)
                           @Valid
                           @RequestBody BookServiceObject newBookData) {

        service.createBook(newBookData);
    }

    @RequestMapping(path = "/{isbn}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a book", notes = "")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String isbn) {
        service.deleteBook(isbn);
    }
}
