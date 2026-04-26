package com.AirBnb.projects.airbnb_app.controller;

import com.AirBnb.projects.airbnb_app.dto.BookingDTO;
import com.AirBnb.projects.airbnb_app.dto.BookingRequest;
import com.AirBnb.projects.airbnb_app.dto.GuestDTO;
import com.AirBnb.projects.airbnb_app.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {

    private final BookingService bookingService;
    @PostMapping("/init")
    public ResponseEntity<BookingDTO> initialiseBooking(@RequestBody BookingRequest bookingRequest) {
        return  ResponseEntity.ok(bookingService.initialiseBooking(bookingRequest));
    }

    @PostMapping("/{bookingId}/addGuests")
    public ResponseEntity<BookingDTO> addGuest(@PathVariable Long bookingId,
                                               @RequestBody List<GuestDTO> guestDtoList) {
        return ResponseEntity.ok(bookingService.addGuests(bookingId, guestDtoList));
    }

    @PostMapping("/booking{id}/payments")
    public ResponseEntity<Map<String, String>> initialPayment(@PathVariable Long bookingId) {
        String sessionUrl = bookingService.initialPayment(bookingId);
        return ResponseEntity.ok(Map.of("sessionUrl", sessionUrl));
    }
}
