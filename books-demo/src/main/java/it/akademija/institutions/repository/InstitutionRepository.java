package it.akademija.institutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

// Norime kad sitos repozitorijos Bean nebutu kuriamas, kadangi mums nereikia atskiros repositorijos bendro objekto
@NoRepositoryBean
public interface InstitutionRepository<T> extends JpaRepository<T,Long> {
    T findByCode(String code);
    boolean existsByCode(String code);

//    @Query('select * from InstitutionEntity ')
//    T findInstitutionsWithLessThanFiveBooks();
}
