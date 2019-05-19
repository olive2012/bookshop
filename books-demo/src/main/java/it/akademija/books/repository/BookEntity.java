package it.akademija.books.repository;

import it.akademija.institutions.repository.InstitutionEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private int numOfPages;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "books")
    private Set<InstitutionEntity> institutions = new HashSet<>();

    /**
     * Removes reference to this book from all institutions before deleting.
     */
    @PreRemove
    private void removeBooksFromInstitutions() {
        for (InstitutionEntity i : institutions) {
            i.getBooks().remove(this);
            // getBooks() - gauti nuoroda i books kolekcija viduje insttitution
            // mes gaunam nuoroda i kolekcija ir is kolekcijos pasaliname sita knyga
        }
    }
}

