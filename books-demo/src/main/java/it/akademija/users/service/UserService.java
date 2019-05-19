package it.akademija.users.service;

import it.akademija.users.repository.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import it.akademija.users.repository.UserRepository;

@Service
public class UserService {

    // UserRepository tipo beansas turi susikurti automatiskai ir prisiskirti prie "repository" lauko
    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public List<UserServiceObject> getUsers() {
        return repository.findAll()
                .stream().map(u ->
                        new UserServiceObject(u.getUsername(),u.getFirstName() ,u.getLastName() , u.getEmail()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createUser(UserServiceObject newUser) {
        //tikirnam ar turime vartotoja su tokiu FirstName ir LastName
        UserEntity existingUser =  repository.findByFirstNameAndLastName(newUser.getFirstName(),newUser.getLastName() );

        if (existingUser != null)
        {
            return;
        }

        String username = newUser.getFirstName().toLowerCase() + "." + newUser.getLastName().toLowerCase();

        UserEntity newUserEntity = new UserEntity();

        newUserEntity.setUsername(username);
        newUserEntity.setFirstName(newUser.getFirstName());
        newUserEntity.setLastName(newUser.getLastName());
        newUserEntity.setEmail(newUser.getEmail());

        repository.save(newUserEntity);
    }

//    @Transactional
//    public void createUser(UserServiceObject newUser) {
//        UserEntity newUserEntity = new UserEntity();
//
//        newUserEntity.setUsername(newUser.getUsername());
//        newUserEntity.setFirstName(newUser.getFirstName());
//        newUserEntity.setLastName(newUser.getLastName());
//        newUserEntity.setEmail(newUser.getEmail());
//
//        repository.save(newUserEntity);
//    }

    @Transactional
    public void deleteUser(String username) {
        repository.deleteByUsername(username);
    }
}