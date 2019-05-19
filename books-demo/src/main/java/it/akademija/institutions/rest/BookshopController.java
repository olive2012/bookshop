package it.akademija.institutions.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import it.akademija.books.service.BookServiceObject;
import it.akademija.institutions.service.BookshopService;
import it.akademija.institutions.service.BookshopServiceObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@Api(value = "bookshop")
@RequestMapping("/api/bookshop")
public class BookshopController {

    private BookshopService service;


    @Autowired
    public void setService(BookshopService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "List all bookshops", notes = "")
    public List<BookshopServiceObject> getAll() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a bookshop", notes = "Creates a bookshop")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@ApiParam(value = "New bookshop data", required = true)
                       @Valid
                       @RequestBody BookshopServiceObject newBookshopData) {

        service.create(newBookshopData);
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete bookshop", notes = "Will not delete books")
    public void delete(@PathVariable final String code) {
        service.delete(code);
    }


    @RequestMapping(path = "/{code}/books/{isbn}", method = RequestMethod.POST)
    @ApiOperation(value = "Add book to library", notes = "")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@ApiParam(value = "Library code", required = true)
                        @PathVariable final String code,
                        @ApiParam(value = "Book ISBN", required = true)
                        @PathVariable final String isbn) {
        service.addBook(code,isbn);
    }

    @RequestMapping(path = "/{code}/books",method = RequestMethod.GET)
    @ApiOperation(value = "Get list of all books in  a library", notes = "Creates a library")
    public Set<BookServiceObject> getBooks(
            @ApiParam(value = "Library code", required = true)
            @PathVariable final String code){
        return service.listAllBooksInInstitution(code);
    }
}
