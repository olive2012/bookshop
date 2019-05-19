package it.akademija.books.service;

import it.akademija.books.repository.BookEntity;
import it.akademija.books.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository repository;

    @Transactional
    public List<BookServiceObject> getAllBooks(){
        return repository.findAll()
                .stream()
                .map(bookEntity -> new BookServiceObject(bookEntity.getIsbn(),
                        bookEntity.getAuthor(),
                        bookEntity.getTitle(),
                        bookEntity.getNumOfPages()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createBook(BookServiceObject newBso){
        // patikrinama ar neturime knygos su tokio ISBN
        if (repository.findByIsbn(newBso.getIsbn()) != null) { return; }

        // matyt neturime. Reikia sukurti.

        BookEntity bookEntity = new BookEntity();

        bookEntity.setIsbn(newBso.getIsbn());
        bookEntity.setAuthor(newBso.getAuthor());
        bookEntity.setTitle(newBso.getTitle());
        bookEntity.setNumOfPages(newBso.getNumOfPages());

        repository.save(bookEntity);
    }

    @Transactional
    public void deleteBook (String isbn){
        BookEntity bookEntityToDelete = repository.findByIsbn(isbn);

        if (bookEntityToDelete != null){
            repository.delete(bookEntityToDelete);
        }
    }

    @PostConstruct
    public void iAmBeingInit(){
        logger.info("Book service being init");
    }

    @PreDestroy
    public void iAmBeingDestroyed()
    {
        logger.info("Book service being destroyed");
    }

}


