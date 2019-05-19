package it.akademija.institutions.service;

import it.akademija.books.repository.BookEntity;
import it.akademija.books.repository.BookRepository;
import it.akademija.books.service.BookServiceObject;
import it.akademija.institutions.repository.InstitutionEntity;
import it.akademija.institutions.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Sitoje klaseje isnesama bendrini Library ir Bookshop funkcionluma
 * pvz. abu juos galima trinti, abiems galima prideti knygas ir perziureti turimu knygu sarasa
 * sitiems veiksmams atlikti uztenka InstitutionRepository ir BookRepository interfeisu
 * (nenaudojam nieko specifinio is LibraryRepository / BookshopRepository
 *
 * Pazymesime klase "abstract" kad zinotume jog jos egzemplioriu negalima kurti. Ja galima tik paveldeti.
 */
public abstract class InstitutionService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /**
     * we define repository here, but it should be set in a child class (like BookshopService or LibraryService)
     */
    InstitutionRepository repository;
    // WE do not provide a setter for repository here
    // but the child class MUST set this property
    // either explicitly or with @autowired setter
    // because if not - we will get lot's of NullPointerException

    /**
     * Deletes an institution
     * @param code
     */
    public void delete(String code){
        InstitutionEntity inst = (InstitutionEntity) repository.findByCode(code);
        if (inst != null) {
            repository.delete(inst);
        }
    }

    /**
     * Adds a book to institutions's book set
     *
     * @param code
     * @param isbn
     */
    public void addBook(String code, String isbn) {
        BookEntity book = bookRepository.findByIsbn(isbn);
        InstitutionEntity inst = (InstitutionEntity) repository.findByCode(code);

        if (book != null && inst != null) {
            inst.getBooks().add(book); // getBooks() - gauti nuoroda i books kolekcija viduje insttitution
            repository.save(inst);
        }
    }

    /**
     * Returns books in institution with given code (if such institution exists)
     *
     * @param code
     * @return books, which this institution has
     */
    //
    public Set<BookServiceObject> listAllBooksInInstitution(String code) {
        InstitutionEntity institution = (InstitutionEntity) repository.findByCode(code);

        if (institution == null) {
            return null;
        }

        return institution.getBooks()
                .stream()
                .map(b -> new BookServiceObject(b.getIsbn(),
                                b.getAuthor(),
                                b.getTitle(),
                                b.getNumOfPages()
                        )
                )
                .collect(Collectors.toSet());

    }
}
