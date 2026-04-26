package com.AirBnb.projects.airbnb_app.service;

import com.AirBnb.projects.airbnb_app.dto.HotelDTO;
import com.AirBnb.projects.airbnb_app.dto.HotelInfoDTO;
import org.jspecify.annotations.Nullable;

public interface HotelService {
    HotelDTO createNewHotel(HotelDTO hotelDto);

    HotelDTO getHotelById (Long id);

    HotelDTO updateHotelById(Long id, HotelDTO hotelDTO);

    void deleteHotelById (Long id);

    void activateHotel(Long hotelId);

    HotelInfoDTO getHotelInfoById(Long hotelId);
}
