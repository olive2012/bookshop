package it.akademija.institutions.service;

import it.akademija.institutions.enums.BookshopSize;
import lombok.Data;

@Data
public class BookshopServiceObject extends InstitutionServiceObject {
    private BookshopSize bookshopSize;
    private boolean acceptsCards;
}
