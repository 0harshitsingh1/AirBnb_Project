package com.AirBnb.projects.airbnb_app.controller;

import com.AirBnb.projects.airbnb_app.dto.BookingDTO;
import com.AirBnb.projects.airbnb_app.dto.HotelDTO;
import com.AirBnb.projects.airbnb_app.dto.HotelReportDTO;
import com.AirBnb.projects.airbnb_app.service.BookingService;
import com.AirBnb.projects.airbnb_app.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private  final HotelService hotelService;
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<HotelDTO> createNewHotel(@RequestBody HotelDTO hotelDTO){
        log.info("Attempting to create a new hotel with name: " + hotelDTO.getName());
        HotelDTO hotel = hotelService.createNewHotel(hotelDTO);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public  ResponseEntity<HotelDTO> getHotelId(@PathVariable Long hotelId){
        HotelDTO hotelDTO = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotelDTO);
    }

    @PutMapping("/{hotelId}")
    public  ResponseEntity<HotelDTO> updateHotelId(@PathVariable Long hotelId, @RequestBody HotelDTO hotelDTO){
        HotelDTO hotel = hotelService.updateHotelById(hotelId, hotelDTO);
        return ResponseEntity.ok(hotel);
    }

    @DeleteMapping("/{hotelId}")
    public  ResponseEntity<HotelDTO> deleteHotelId(@PathVariable Long hotelId){
        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{hotelId}/activate")
    public  ResponseEntity<HotelDTO> activeHotelId(@PathVariable Long hotelId){
        hotelService.activateHotel(hotelId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{hotelId}/bookings")
    public ResponseEntity<List<BookingDTO>> getAllBookingsByHotelId(@PathVariable Long hotelId) {
        return ResponseEntity.ok(bookingService.getAllBookingsByHotelId(hotelId));
    }

    @GetMapping("/{hotelId}/reports")
    public ResponseEntity<HotelReportDTO> getHotelReport(@PathVariable Long hotelId,
                                                                  @RequestParam(required = false)LocalDate startDate,
                                                                  @RequestParam(required = false)LocalDate endDate) {

        if(startDate == null) startDate = LocalDate.now().minusMonths(1);
        if(endDate == null) endDate = LocalDate.now();
        return ResponseEntity.ok(bookingService.getHotelReport(hotelId, startDate , endDate));
    }
}
