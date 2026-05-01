package com.AirBnb.projects.airbnb_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class HotelReportDTO {

    private Long bookingCount;
    private BigDecimal totalCount;
    private BigDecimal avgRevenue;
}
