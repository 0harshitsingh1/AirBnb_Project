package com.AirBnb.projects.airbnb_app.dto;

import com.AirBnb.projects.airbnb_app.entity.HotelContactInfo;
import lombok.Data;

@Data
public class HotelDTO {

    private Long id;
    private  String name;
    private  String city;
    private  String[] photos;
    private  String[] amenties;
    private HotelContactInfo contactInfo;
    private Boolean active;
}
