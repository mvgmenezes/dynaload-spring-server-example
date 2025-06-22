package io.dynaload.example.spring.server.service;

import io.dynaload.annotations.DynaloadCallable;
import io.dynaload.annotations.DynaloadService;
import io.dynaload.example.spring.server.model.User;
import io.dynaload.example.spring.server.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@DynaloadService
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @DynaloadCallable
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DynaloadCallable
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @DynaloadCallable
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @DynaloadCallable
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
