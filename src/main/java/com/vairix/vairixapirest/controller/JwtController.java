package com.vairix.vairixapirest.controller;

import com.vairix.vairixapirest.exception.BadRequestException;
import com.vairix.vairixapirest.exception.EmailExistException;
import com.vairix.vairixapirest.exception.InvalidEmailException;
import com.vairix.vairixapirest.model.BearerToken;
import com.vairix.vairixapirest.model.LoginDto;
import com.vairix.vairixapirest.model.User;
import com.vairix.vairixapirest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping(JWT_URI_BASE+"/register")
    public BearerToken register(@RequestBody User user) throws EmailExistException, BadRequestException, InvalidEmailException {
        return userService.register(user);
    }

    @PostMapping(JWT_URI_BASE+"/authenticate")
    public BearerToken authenticate(@RequestBody LoginDto login) {
        return userService.authenticate(login);
    }

}
