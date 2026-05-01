package com.AirBnb.projects.airbnb_app.service;

import com.AirBnb.projects.airbnb_app.dto.RoomDTO;

import java.util.List;
public interface RoomService {

    RoomDTO createNewRoom(Long hotelId, RoomDTO roomDto);

    List<RoomDTO> getAllRoomsInHotel(Long hotelId);

    RoomDTO getRoomById(Long roomId);

    void deleteRoomById(Long roomId);

    RoomDTO updateRoomById(Long hotelId, Long roomId, RoomDTO roomDTO);
}
