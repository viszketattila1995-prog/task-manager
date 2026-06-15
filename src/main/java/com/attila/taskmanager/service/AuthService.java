package com.attila.taskmanager.service;

import com.attila.taskmanager.config.security.JwtService;
import com.attila.taskmanager.domain.AppUser;
import com.attila.taskmanager.dto.request.LoginCommand;
import com.attila.taskmanager.dto.response.JwtResponse;
import com.attila.taskmanager.exception.WrongPasswordException;
import com.attila.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public JwtResponse loginWithJwtToken(LoginCommand command) {

        AppUser appUser = userRepository.findByUsername(command.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Invalid credentials"));

        if (!passwordEncoder.matches(command.getPassword(), appUser.getPassword())) {
            throw new WrongPasswordException("Invalid credentials");
        }

        return new JwtResponse(jwtService.generateToken(command.getUsername()));
    }
}
