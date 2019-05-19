package it.akademija.users.service;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data // speciali lombok anotacija, kuri sugeneruos Setterius, Getterius ir t.t.
// sis objektas bus naudojamas
public class UserCreateCommand {
    @NotNull
    @Length(min = 1, max = 100)
    private String firstName;

    @NotNull
    @Length(min = 1, max = 100)
    private String lastName;

    @NotNull
    @Length(min = 1, max = 200)
    @Email
    private String email;
}
