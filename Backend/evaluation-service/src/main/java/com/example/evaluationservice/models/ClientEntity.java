package com.example.evaluationservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    private Long id;
    private String rut;
    private String name;
    private LocalDate birthDate;
    private String password;
}
