package com.AirBnb.projects.airbnb_app.service;

import com.AirBnb.projects.airbnb_app.dto.HotelDTO;
import com.AirBnb.projects.airbnb_app.entity.Hotel;
import com.AirBnb.projects.airbnb_app.entity.Room;
import com.AirBnb.projects.airbnb_app.exception.ResourceNotFoundException;
import com.AirBnb.projects.airbnb_app.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private  final HotelRepository hotelRepository;
    private  final ModelMapper modelMapper;
    private final InventoryService inventoryService;

    @Override
    public HotelDTO createNewHotel(HotelDTO hotelDto) {
        log.info("Creating a new hotel with name: {}", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class); // we used ModelMapper here -->
        // ModelMapper is a Java library used to automatically map data from one object to another.(DTO → Entity, Entity → DTO)
        hotel.setActive(false); // It setActive(false) marks that hotel as inactive.
        hotel = hotelRepository.save(hotel);
        log.info("CreatED a new hotel with ID: {}", hotelDto.getId());
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        log.info("Getting the hotel with id: {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+ id));
        return  modelMapper.map(hotel, HotelDTO.class);

    }

    @Override
    public HotelDTO updateHotelById(Long id, HotelDTO hotelDTO) {
        log.info("Updating the hotel with id: {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+ id));
        modelMapper.map(hotelDTO, hotel);
        hotel.setId((id));
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        log.info("Activating the hotel with ID: {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with ID: " + id));
        hotelRepository.deleteById(id);
        for(Room room: hotel.getRooms()){
            inventoryService.deleteFutureInventories(room);
        }
    }

    @Override
    @Transactional
    public void activateHotel(Long hotelId) {
        log.info("Activating the hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with ID: " + hotelId));
        hotel.setActive(true);

        //assuming only do it once
        for(Room room: hotel.getRooms()){
            inventoryService.initializeRoomForAYear(room);
        }
    }
}
