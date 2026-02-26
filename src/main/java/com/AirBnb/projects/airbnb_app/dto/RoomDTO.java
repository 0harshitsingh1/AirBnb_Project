package com.AirBnb.projects.airbnb_app.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RoomDTO {

    private  Long id;
    private  String type;
    private BigDecimal basePrice;
    private  String[] photos;
    private  String[] amenties;
    private Integer totalCount;
    private Integer capacity;
}
