package com.AirBnb.projects.airbnb_app.dto;

import com.AirBnb.projects.airbnb_app.entity.enums.Role;
import lombok.Data;

@Data
public class SignUpRequestDTO {
    private String email;
    private String password;
    private String name;
    private Role roles;
}
