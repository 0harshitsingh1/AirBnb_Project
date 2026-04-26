package com.AirBnb.projects.airbnb_app.service;

import com.AirBnb.projects.airbnb_app.dto.BookingDTO;
import com.AirBnb.projects.airbnb_app.dto.BookingRequest;
import com.AirBnb.projects.airbnb_app.dto.GuestDTO;
import com.AirBnb.projects.airbnb_app.entity.Booking;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface BookingService {
    BookingDTO initialiseBooking(BookingRequest bookingRequest);

    BookingDTO addGuests(Long bookingId, List<GuestDTO> guestDTOList);

    String initialPayment(Long bookingId);
}
