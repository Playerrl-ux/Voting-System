package org.example.voting.api.controller;

import org.example.voting.api.dto.UserDTO;
import org.example.voting.api.model.User;
import org.example.voting.api.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(@RequestBody UserDTO dto) {

        Optional<User> search = userRepository.findByEmail(dto.email());
        if(search.isPresent()) {
            return ResponseEntity.badRequest().
                    body(Map.of("message", "Email already in use"));
        }
        System.out.println(dto);
        userRepository.save(new User(dto.name(), dto.email()));
        return ResponseEntity.ok().
                body(Map.of("message", "User Created Successfully"));
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        Optional<User> search = userRepository.findByEmail(email);
        if(search.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().
                body(UserDTO.from(search.get()));
    }
}
