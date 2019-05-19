package it.akademija.institutions.service;

import it.akademija.institutions.repository.LibraryEntity;
import it.akademija.institutions.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService extends InstitutionService {

    @Autowired
    public void setRepository(LibraryRepository repository) {
        this.repository = repository;
    }

    public List<LibraryServiceObject> getAll() {
             return ((LibraryRepository) repository)
                .findAll()
                .stream()
                .map(library -> EntityToSO(library))
                .collect(Collectors.toList());
    }

    public void create(LibraryServiceObject so) {
        if (!repository.existsByCode(so.getCode())) {
            repository.save(EntityFromSO(so));
        }

    }

    private static LibraryServiceObject EntityToSO(LibraryEntity entity) {
        LibraryServiceObject so = new LibraryServiceObject();
        so.setName(entity.getName());
        so.setCode(entity.getCode());
        so.setLibrarySize(entity.getLibrarySize());
        so.setHasReadingHall(entity.isHasReadingHall());
        return so;
    }

    private static LibraryEntity EntityFromSO(LibraryServiceObject so) {
        LibraryEntity entity = new LibraryEntity();
        entity.setName(so.getName());
        entity.setCode(so.getCode());
        entity.setLibrarySize(so.getLibrarySize());
        entity.setHasReadingHall(so.isHasReadingHall());
        return entity;
    }

}
