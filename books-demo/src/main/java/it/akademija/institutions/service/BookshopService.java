package it.akademija.institutions.service;

import it.akademija.institutions.repository.BookshopEntity;
import it.akademija.institutions.repository.BookshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookshopService extends InstitutionService{
    @Autowired
    public void setRepository(BookshopRepository repository) {
        this.repository = repository;
    }

    public List<BookshopServiceObject> getAll()
    {
        return ((BookshopRepository) repository)
                .findAll()
                .stream()
                .map(bs -> EntityToSO((BookshopEntity) bs))
                .collect(Collectors.toList());
    }

    public void create(BookshopServiceObject so){
        if (!repository.existsByCode(so.getCode())) {
            repository.save(EntityFromSO(so));
        }
    }

    /**
     * Papildoma funkcija naudojama paversti BookshopEntity -> BookshopServiceObject (Service Object)
     * @param entity
     * @return BookshopServiceObject (Service object)
     */
    private static BookshopServiceObject EntityToSO(BookshopEntity entity){
        BookshopServiceObject so = new BookshopServiceObject();
        so.setName(entity.getName());
        so.setCode(entity.getCode());
        so.setBookshopSize(entity.getBookshopSize());
        so.setAcceptsCards(entity.isAcceptsCards());
        return so;
    }


    /**
     * Papildoma funkcija naudojama paversti BookshopServiceObject (Service Object) -> BookshopEntity
     * @param so
     * @return
     */
    private static BookshopEntity EntityFromSO(BookshopServiceObject so){
        BookshopEntity entity = new BookshopEntity();
        entity.setName(so.getName());
        entity.setCode(so.getCode());
        entity.setBookshopSize(so.getBookshopSize());
        entity.setAcceptsCards(so.isAcceptsCards());
        return entity;
    }
}
