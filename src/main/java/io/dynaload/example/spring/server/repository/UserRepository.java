package io.dynaload.example.spring.server.repository;

import io.dynaload.example.spring.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}

