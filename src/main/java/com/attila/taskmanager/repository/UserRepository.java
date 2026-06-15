package com.attila.taskmanager.repository;

import com.attila.taskmanager.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    boolean existsUserByUsername(String username);

    Optional<AppUser> findByUsername(String username);
}
