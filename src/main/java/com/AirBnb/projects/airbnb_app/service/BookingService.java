package com.AirBnb.projects.airbnb_app.service;

import com.AirBnb.projects.airbnb_app.dto.BookingDTO;
import com.AirBnb.projects.airbnb_app.dto.BookingRequest;
import com.AirBnb.projects.airbnb_app.dto.GuestDTO;
import com.AirBnb.projects.airbnb_app.dto.HotelReportDTO;
import com.stripe.model.Event;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    BookingDTO initialiseBooking(BookingRequest bookingRequest);

    BookingDTO addGuests(Long bookingId, List<GuestDTO> guestDTOList);

    String initialPayment(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    String getBookingStatus(Long bookingId);

    List<BookingDTO> getAllBookingsByHotelId(Long hotelId);

    HotelReportDTO getHotelReport(Long hotelId, LocalDate startDate, LocalDate endDate);
}
