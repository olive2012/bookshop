package it.akademija.institutions.repository;

import it.akademija.institutions.enums.BookshopSize;
import it.akademija.institutions.repository.InstitutionEntity;
import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.*;

@Data
@Entity
@DiscriminatorValue(value = "Bookshop") // pagal sita irasa mes DB lenteleje atskirsim koks cia objektas
public class BookshopEntity extends InstitutionEntity {
    @Enumerated(EnumType.ORDINAL)
    //objekto laukai
    private BookshopSize bookshopSize;
    private boolean acceptsCards;



}
