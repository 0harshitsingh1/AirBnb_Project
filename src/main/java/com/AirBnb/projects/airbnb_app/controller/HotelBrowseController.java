package com.AirBnb.projects.airbnb_app.controller;

import com.AirBnb.projects.airbnb_app.dto.HotelDTO;
import com.AirBnb.projects.airbnb_app.dto.HotelInfoDTO;
import com.AirBnb.projects.airbnb_app.dto.HotelPriceDTO;
import com.AirBnb.projects.airbnb_app.dto.HotelSearchRequest;
import com.AirBnb.projects.airbnb_app.service.HotelService;
import com.AirBnb.projects.airbnb_app.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page; // <-- Correct Spring Data import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelPriceDTO>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest) {
        var page = inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDTO> getHotelInfo (@PathVariable Long hotelId){
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));

    }
}