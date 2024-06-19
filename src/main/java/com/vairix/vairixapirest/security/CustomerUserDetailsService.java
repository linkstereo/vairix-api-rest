package com.vairix.vairixapirest.security;

import com.vairix.vairixapirest.model.User;
import com.vairix.vairixapirest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository iUserRepository;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = iUserRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found !"));
        return  user;

    }


}
