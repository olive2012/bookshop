package it.akademija.users.service;

import lombok.Data;

@Data
// objektas, kuris bus atiduodamas REST klientams (pvz. React)
public class UserServiceObject {
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public UserServiceObject(String username, String firstName, String lastName, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
