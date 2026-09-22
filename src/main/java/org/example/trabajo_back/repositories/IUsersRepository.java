package org.example.trabajo_back.repositories;

import org.example.trabajo_back.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
