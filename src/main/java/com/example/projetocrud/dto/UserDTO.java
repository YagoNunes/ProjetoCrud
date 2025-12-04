package com.example.projetocrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private  Integer  id ;
    private  String  name ;
    private  String  email;
    private  String  cpf;
    private  LocalDate birthday;
    private  String  password;

}
