package com.AirBnb.projects.airbnb_app.service;

import com.AirBnb.projects.airbnb_app.entity.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking bookingId, String sucessUrl, String failureUrl);
}
