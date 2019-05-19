package it.akademija.institutions.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import it.akademija.books.service.BookServiceObject;
import it.akademija.institutions.service.LibraryService;
import it.akademija.institutions.service.LibraryServiceObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@Api(value = "library")
@RequestMapping("/api/library")
public class LibraryController {
    private LibraryService service;

    @Autowired
    public void setService(LibraryService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "List all libraries", notes = "")
    public List<LibraryServiceObject> getAll() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a library", notes = "Creates a library")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@ApiParam(value = "New library data", required = true)
                       @Valid
                       @RequestBody LibraryServiceObject newLibraryData) {

        service.create(newLibraryData);
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete library", notes = "Will not delete books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
