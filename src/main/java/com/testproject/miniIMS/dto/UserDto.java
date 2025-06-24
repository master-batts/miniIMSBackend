package com.testproject.miniIMS.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String name;
    private String username;
    private String email;
    private Set<String> roles;
}