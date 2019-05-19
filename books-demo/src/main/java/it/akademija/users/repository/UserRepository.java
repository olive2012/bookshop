package it.akademija.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// pridedam JpaRepository, kuris vien tik savo egzistavimu antesa metodus
// save() ir t.t., ir papildomai leidzia aprasyti naujus
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // papildome metodu, kuris ieško UserEntity pagal tikslų email
    // realizacija mums sugeneruos Spring Data pagal metodo pavadinima
    UserEntity findByEmail(String email);

    // papildome metodu, kuris trina visus UserEntity pagal username
    // realizacija mums sugeneruos Spring Data pagal metodo pavadinima
    void deleteByUsername(String username);

 //   UserEntity findByLastNameAndFirstName(String firstName, String lastName);

    UserEntity findByFirstNameAndLastName(String firstName, String lastName);

}
