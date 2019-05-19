package it.akademija.institutions.repository;

import it.akademija.institutions.enums.LibrarySize;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@DiscriminatorValue(value = "Library") // pagal sita irasa mes DB lenteleje atskirsim koks cia objektas
public class LibraryEntity extends InstitutionEntity {
    @Enumerated(EnumType.STRING)

    //klases objekto laukai
    private LibrarySize librarySize;
    private boolean hasReadingHall;

}
