package com.example.projetocrud.service;

import com.example.projetocrud.dto.UserDTO;
import com.example.projetocrud.model.User;
import com.example.projetocrud.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepository;

    public UserDTO create(UserDTO userDTO) {

        User newUser = new User();
        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());
        newUser.setCpf(userDTO.getCpf());
        newUser.setBirthDate(userDTO.getBirthday());

        User savedUser = userRepository.save(newUser);

        UserDTO responseDTO = new UserDTO();
        responseDTO.setId(savedUser.getId());
        responseDTO.setName(savedUser.getName());
        responseDTO.setEmail(savedUser.getEmail());
        responseDTO.setCpf(savedUser.getCpf());
        responseDTO.setBirthday(savedUser.getBirthDate());

        return responseDTO;
    }

    public UserDTO login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Senha incorreta");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
}
