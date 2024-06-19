package com.vairix.vairixapirest.service;

import com.vairix.vairixapirest.exception.BadRequestException;
import com.vairix.vairixapirest.exception.EmailExistException;
import com.vairix.vairixapirest.exception.InvalidEmailException;
import com.vairix.vairixapirest.exception.UserNotExistException;
import com.vairix.vairixapirest.model.BearerToken;
import com.vairix.vairixapirest.model.LoginDto;
import com.vairix.vairixapirest.model.User;
import com.vairix.vairixapirest.repository.UserRepository;
import com.vairix.vairixapirest.security.JwtUtilities;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public BearerToken register(User user) throws EmailExistException, BadRequestException, InvalidEmailException {
        var exist = userRepository.findByEmail(user.getEmail());

        if ( isBlank(user.getEmail()))
            throw new BadRequestException("El correo no puede ser nulo o vacio");

        if (!EmailValidator.getInstance().isValid(user.getEmail()))
            throw new InvalidEmailException();

        if (exist.isPresent()){
            throw new EmailExistException();
        }

        var encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user = userRepository.save(user);

        String token = jwtUtilities.generateToken(user.getEmail());
        return new BearerToken(token , "Bearer ");
    }

    public User updateUser(User user) throws UserNotExistException, BadRequestException, InvalidEmailException {
        var savedUser = getSavedUser(user);

        if (savedUser.isEmpty()){
            throw new UserNotExistException();
        }  else {

            if (isBlank(user.getName())){
                user.setName(savedUser.get().getName());
            }

            var encodedPassword = passwordEncoder.encode(savedUser.get().getPassword());
            user.setPassword(encodedPassword);
            user.setId(savedUser.get().getId());
            user.setActive(user.isActive());
        }

        return userRepository.save(user);
    }

    private Optional<User> getSavedUser(User user) throws BadRequestException, InvalidEmailException {
        if ( isNull(user.getId()) && isBlank(user.getEmail()))
            throw new BadRequestException("Amos campos (id y email) no pueder ser null o vacio");

        if (!EmailValidator.getInstance().isValid(user.getEmail()))
            throw new InvalidEmailException();

        Optional<User> savedUser = null;
        if (!isNull(user.getId()))
            savedUser = userRepository.findById(user.getId());
        else if (!isBlank(user.getEmail()))
            savedUser = userRepository.findByEmail(user.getEmail());
        else
            savedUser = Optional.empty();

        return savedUser;
    }


    public BearerToken authenticate(LoginDto loginDto) {
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User savedUser = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String token = jwtUtilities.generateToken(savedUser.getEmail());
        return new BearerToken(token , "Bearer ");
    }
}
