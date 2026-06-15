package com.attila.taskmanager.service;

import com.attila.taskmanager.domain.AppUser;
import com.attila.taskmanager.dto.request.CreateUserCommand;
import com.attila.taskmanager.exception.UserAlreadyExistsException;
import com.attila.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Long registerUser(CreateUserCommand createUserCommand) {

        if (userRepository.existsUserByUsername(createUserCommand.getUsername())) {
            throw new UserAlreadyExistsException("User with this username is already exists");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(createUserCommand.getUsername());
        appUser.setPassword(passwordEncoder.encode(createUserCommand.getPassword()));
        return userRepository.save(appUser).getId();
    }

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) {

        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .build();
    }
}
