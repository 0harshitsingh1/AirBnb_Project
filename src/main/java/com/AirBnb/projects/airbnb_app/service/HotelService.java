package com.AirBnb.projects.airbnb_app.service;

import com.AirBnb.projects.airbnb_app.dto.HotelDTO;

public interface HotelService {
    HotelDTO createNewHotel(HotelDTO hotelDto);

    HotelDTO getHotelById (Long id);

    HotelDTO updateHotelById(Long id, HotelDTO hotelDTO);

    void deleteHotelById (Long id);

    void activateHotel(Long hotelId);
}
