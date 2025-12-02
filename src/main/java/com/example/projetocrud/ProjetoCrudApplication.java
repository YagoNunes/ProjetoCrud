package com.example.projetocrud;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjetoCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoCrudApplication.class, args);
    }
        @Bean
    public ModelMapper modelMapper ( ) {
        return  new  ModelMapper ();
    }
}
