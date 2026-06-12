package com.attila.taskmanager.service;

import com.attila.taskmanager.domain.User;
import com.attila.taskmanager.dto.request.CreateUserCommand;
import com.attila.taskmanager.exception.UserAlreadyExistsException;
import com.attila.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Long registerUser(CreateUserCommand createUserCommand) {

        if (userRepository.existsUserByUsername(createUserCommand.getUsername())) {
            throw new UserAlreadyExistsException("User with this username is already exists");
        }

        User user = new User();
        user.setUsername(createUserCommand.getUsername());
        user.setPassword(passwordEncoder.encode(createUserCommand.getPassword()));
        return userRepository.save(user).getId();
    }
}
