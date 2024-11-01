package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.repository;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
