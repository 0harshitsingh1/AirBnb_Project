package com.AirBnb.projects.airbnb_app.dto;

import com.AirBnb.projects.airbnb_app.entity.User;
import com.AirBnb.projects.airbnb_app.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class GuestDTO {

    private  Long id;
    private User user;
    private String name;
    private Gender gander;
    private Integer age;
}
