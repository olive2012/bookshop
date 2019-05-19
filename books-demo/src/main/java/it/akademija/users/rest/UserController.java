package it.akademija.users.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.akademija.users.service.UserCreateCommand;
import it.akademija.users.service.UserService;
import it.akademija.users.service.UserServiceObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "user")
@RequestMapping("/api/users")
public class UserController {
    // service aptarnaus Rest Controllerio uzklausas

    private UserService service;

    // sukuriam setteri su @Autowired, kas reiskia, jog tinkama UserService beana turi parupinti Spring

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    /* Apdoros užklausas: GET /api/users */

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get users", notes = "Returns registered users")
    public List<UserServiceObject> getUsers() {
        return service.getUsers();
    }

    /* Sukurs vartotoją ir grąžins atsakymą su HTTP statusu 201 */

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create user", notes = "Creates user with data")
    @ResponseStatus(HttpStatus.CREATED)
    // Panaudojame "UserEntity" grazinama tipa, kadangi norime kad po to kai UserEntity bus sukurtas,
    // jo objektas butu grazintas naudotojui. Atsakyme bus ir naujai sukurto objekto ID

    public void createUser(@ApiParam(value = "UserEntity Data", required = true)
                           @Valid
                           @RequestBody UserCreateCommand cmd) {

        UserServiceObject newUser = new UserServiceObject("", cmd.getFirstName(), cmd.getLastName(), cmd.getEmail());
        service.createUser(newUser);
    }

    /* Apdoros užklausas: DELETE /api/users/<vartotojas> */

    @RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable final String username) {
        service.deleteUser(username);
    }
}
