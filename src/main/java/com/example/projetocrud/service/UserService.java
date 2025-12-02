package com.example.projetocrud.service;

import com.example.projetocrud.dto.UserDTO;
import com.example.projetocrud.model.User;
import com.example.projetocrud.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ModelMapper modelMapper;

    // LISTAR TODOS
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return modelMapper.map(userList, new TypeToken<List<UserDTO>>() {}.getType());
    }

    // SALVAR USUÁRIO
    public UserDTO saveUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return userDTO;
    }

    //ATUALIZAR USUÁRIO
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return userDTO;
    }

    //DELETAR USUÁRIO
    public String deleteUser(Long userId) {
        userRepository.deleteById(Math.toIntExact(userId));
        return "Usuário excluído com sucesso!";
    }
}
