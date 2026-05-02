package com.AirBnb.projects.airbnb_app.repository;

import com.AirBnb.projects.airbnb_app.entity.Booking;
import com.AirBnb.projects.airbnb_app.entity.Hotel;
import com.AirBnb.projects.airbnb_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByPaymentSessionId(String paymentSessionId);

    List<Booking> findByHotel(Hotel hotel);

    List<Booking> findByHotelAndCreatedAtBetween(Hotel hotel, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Booking> findByUser(User user);
}