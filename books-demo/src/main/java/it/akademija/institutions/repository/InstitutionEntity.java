package it.akademija.institutions.repository;

import it.akademija.books.repository.BookEntity;
import lombok.Data;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "INSTITUTION")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Saugosim visus kas paveldes vienoje lenteleje. Library ir Bookshop bus vienoje lenteleje
@DiscriminatorColumn(name = "INSTITUTION_TYPE") // Atskirsim pagal toki stulpeli ar cia Library ar Bookshop
public abstract class InstitutionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;


    // Panaudosim FetchType.LAZY - kad kiekviena karta kai traukiam instituciju sarasa,
    // programa netrauktu is DB visu knygu, kurios yra toje institucijoje
    // sarysiai (kokios knygos yra kokiose institucijose) gules lenteleje "inst_books" su 2 stulpeliais:
    // "inst_id" ir "book_id"
    // kurie yra nurodosos (raktai) i atitinkamus objektus
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    // JoinTable anotacija nurodo lenteles, kurioje bus saugomi Many-to-Many sarysiai, parametrus
    @JoinTable(name = "inst_books",
               joinColumns = { @JoinColumn(name = "inst_id") },
               inverseJoinColumns = { @JoinColumn(name = "book_id") })
    private Set<BookEntity> books = new HashSet<BookEntity>();  //spring apazista kokia antra lentela pagal <BookEntity>

}
