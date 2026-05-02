package com.AirBnb.projects.airbnb_app.dto;

import com.AirBnb.projects.airbnb_app.entity.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileUpdateRequestDTO {

    private String name;
    private LocalDate DateOfBirth;
    private Gender gender;
}
