package com.example.projetocrud.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreateDTO {

    private  String name;
    private String email;
    private String password;
    private String cpf;
    private LocalDate birthday;
}
