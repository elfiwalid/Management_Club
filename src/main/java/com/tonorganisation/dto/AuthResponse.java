package com.tonorganisation.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String message;
    private String role;
    private int id;        
    private String name;  
    private Integer joueurId; 
}