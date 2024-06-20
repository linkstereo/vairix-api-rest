package com.vairix.vairixapirest.controller;

import com.vairix.vairixapirest.exception.BadRequestException;
import com.vairix.vairixapirest.exception.InvalidEmailException;
import com.vairix.vairixapirest.exception.UserNotExistException;
import com.vairix.vairixapirest.model.User;
import com.vairix.vairixapirest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.vairix.vairixapirest.controller.ControllerConstants.BASE_API_VERSION;
import static com.vairix.vairixapirest.controller.ControllerConstants.USERS_URI_BASE;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/"+BASE_API_VERSION+"/")
public class UserController {

    private final UserService userService;

    @CrossOrigin(origins = "*")
    @GetMapping(value = USERS_URI_BASE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll(){
        return userService.getAll();
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value = USERS_URI_BASE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user) throws UserNotExistException, BadRequestException, InvalidEmailException {
        return userService.updateUser(user);
    }

}
