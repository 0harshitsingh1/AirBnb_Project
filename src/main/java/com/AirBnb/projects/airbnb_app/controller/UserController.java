package com.AirBnb.projects.airbnb_app.controller;

import com.AirBnb.projects.airbnb_app.dto.BookingDTO;
import com.AirBnb.projects.airbnb_app.dto.ProfileUpdateRequestDTO;
import com.AirBnb.projects.airbnb_app.dto.UserDTO;
import com.AirBnb.projects.airbnb_app.service.BookingService;
import com.AirBnb.projects.airbnb_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BookingService bookingService;

    @PatchMapping("/profile")
    public ResponseEntity<Void> updateProfile(@RequestBody ProfileUpdateRequestDTO profileUpdateRequestDTO) {
        userService.userProfile(profileUpdateRequestDTO);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/myBookings")
//    @Operation(summary = "Get all my previous bookings", tags = {"Profile"})
    public ResponseEntity<List<BookingDTO>> getMyBookings() {
        return ResponseEntity.ok(bookingService.getMyBookings());
    }

    @GetMapping("/profile")
//    @Operation(summary = "Get my Profile", tags = {"Profile"})
    public ResponseEntity<UserDTO> getMyProfile() {
        return ResponseEntity.ok(userService.getMyProfile());
    }

//    @GetMapping("/guests")
//    @Operation(summary = "Get all my guests", tags = {"Booking Guests"})
//    public ResponseEntity<List<GuestDto>> getAllGuests() {
//        return ResponseEntity.ok(guestService.getAllGuests());
//    }
//
//    @PostMapping("/guests")
//    @Operation(summary = "Add a new guest to my guests list", tags = {"Booking Guests"})
//    public ResponseEntity<GuestDto> addNewGuest(@RequestBody GuestDto guestDto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(guestService.addNewGuest(guestDto));
//    }
//
//    @PutMapping("guests/{guestId}")
//    @Operation(summary = "Update a guest", tags = {"Booking Guests"})
//    public ResponseEntity<Void> updateGuest(@PathVariable Long guestId, @RequestBody GuestDto guestDto) {
//        guestService.updateGuest(guestId, guestDto);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("guests/{guestId}")
//    @Operation(summary = "Remove a guest", tags = {"Booking Guests"})
//    public ResponseEntity<Void> deleteGuest(@PathVariable Long guestId) {
//        guestService.deleteGuest(guestId);
//        return ResponseEntity.noContent().build();
//    }
}
