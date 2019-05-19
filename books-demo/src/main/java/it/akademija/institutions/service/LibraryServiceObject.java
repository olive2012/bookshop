package it.akademija.institutions.service;

import it.akademija.institutions.enums.LibrarySize;
import lombok.Data;

@Data
public class LibraryServiceObject extends InstitutionServiceObject {
    private LibrarySize librarySize;
    private boolean hasReadingHall;
}
