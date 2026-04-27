package com.AirBnb.projects.airbnb_app.repository;

import com.AirBnb.projects.airbnb_app.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByPaymentSessionId(String paymentSessionId);
}