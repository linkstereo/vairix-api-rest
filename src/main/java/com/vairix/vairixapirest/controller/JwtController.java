package com.vairix.vairixapirest.controller;

import com.vairix.vairixapirest.exception.BadRequestException;
import com.vairix.vairixapirest.exception.EmailExistException;
import com.vairix.vairixapirest.exception.InvalidEmailException;
import com.vairix.vairixapirest.model.BearerToken;
import com.vairix.vairixapirest.model.LoginDto;
import com.vairix.vairixapirest.model.User;
import com.vairix.vairixapirest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.vairix.vairixapirest.controller.ControllerConstants.BASE_API_VERSION;
import static com.vairix.vairixapirest.controller.ControllerConstants.JWT_URI_BASE;

/**
 * Permite registrar o bien autenticar a un {@link User}
 */
@RestController()
@RequestMapping("/"+BASE_API_VERSION+"/")
public class JwtController {

    @Autowired
    private UserService userService;

    /**
     * Permite registrar un usuario. El password es codificado antes de ser guardado en la base de datos.
     * Valida el email usando el Validator de Apache.
     * @param user
     * @return
     * @throws EmailExistException
     * @throws BadRequestException
     * @throws InvalidEmailException
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = JWT_URI_BASE+"/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BearerToken register(@RequestBody User user) throws EmailExistException, BadRequestException, InvalidEmailException {
        return userService.register(user);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = JWT_URI_BASE+"/authenticate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BearerToken authenticate(@RequestBody LoginDto login) {
        return userService.authenticate(login);
    }

}
