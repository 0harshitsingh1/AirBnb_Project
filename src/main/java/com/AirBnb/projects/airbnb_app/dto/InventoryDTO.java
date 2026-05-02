package com.AirBnb.projects.airbnb_app.dto;


import com.AirBnb.projects.airbnb_app.entity.Hotel;
import com.AirBnb.projects.airbnb_app.entity.Room;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class InventoryDTO {

    private Long id;
    private LocalDate date;
    private Integer bookedCount;
    private Integer reservedCount;
    private Integer totalCount;
    private BigDecimal surgeFactor;
    private BigDecimal price; // basePrice * surgeFactor
    private Boolean closed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
