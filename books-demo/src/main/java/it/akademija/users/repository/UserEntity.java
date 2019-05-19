package it.akademija.users.repository;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true) // neleidziam tuscio ir pasikartojancio username
    private String username;

    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String email;


}
