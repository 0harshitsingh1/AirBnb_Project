package com.AirBnb.projects.airbnb_app.service;

import com.AirBnb.projects.airbnb_app.dto.HotelDTO;
import com.AirBnb.projects.airbnb_app.dto.HotelInfoDTO;
import com.AirBnb.projects.airbnb_app.dto.RoomDTO;
import com.AirBnb.projects.airbnb_app.entity.Hotel;
import com.AirBnb.projects.airbnb_app.entity.Room;
import com.AirBnb.projects.airbnb_app.entity.User;
import com.AirBnb.projects.airbnb_app.exception.ResourceNotFoundException;
import com.AirBnb.projects.airbnb_app.exception.UnAuthorisedException;
import com.AirBnb.projects.airbnb_app.repository.HotelRepository;
import com.AirBnb.projects.airbnb_app.repository.RoomRepository;
import com.AirBnb.projects.airbnb_app.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.AirBnb.projects.airbnb_app.util.AppUnits.getCurrentUser;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    private  final HotelRepository hotelRepository;
    private  final ModelMapper modelMapper;
    private final InventoryService inventoryService;

    @Override
    public HotelDTO createNewHotel(HotelDTO hotelDto) {
        log.info("Creating a new hotel with name: {}", hotelDto.getName());  // It logs a message showing the hotel name while creating it .
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class); // we used ModelMapper here -->
        // ModelMapper is a Java library used to automatically map data from one object to another.(DTO → Entity, Entity → DTO)
        hotel.setActive(true); // It setActive(true) marks that hotel as active.

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        hotel.setOwner(user);

        hotel = hotelRepository.save(hotel);
        log.info("Created a new hotel with ID: {}", hotelDto.getId());
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        log.info("Getting the hotel with id: {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+ id));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!user.equals(hotel.getOwner())) {
            throw new UnAuthorisedException("This user does not own this hotel with id: "+id);
        }
        return  modelMapper.map(hotel, HotelDTO.class);

    }

    @Override
    public HotelDTO updateHotelById(Long id, HotelDTO hotelDTO) {
        log.info("Updating the hotel with id: {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+ id));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())) {
            throw new UnAuthorisedException("This user does not own this hotel with id: "+id);
        }

        modelMapper.map(hotelDTO, hotel);
        hotel.setId((id));
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with ID: " + id));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())) {
            throw new UnAuthorisedException("This user does not own this hotel with id: "+id);
        }

        for(Room room: hotel.getRooms()){
            inventoryService.deleteFutureInventories(room);
            roomRepository.deleteById(room.getId());
        }
        hotelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void activateHotel(Long hotelId) {
        log.info("Activating the hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!user.equals(hotel.getOwner())) {
            throw new UnAuthorisedException("This user does not own this hotel with id: "+hotelId);
        }

        hotel.setActive(true);
        //assuming only do it once
        for(Room room: hotel.getRooms()){
            inventoryService.initializeRoomForAYear(room);
        }
    }

//    public method
    @Override
    public HotelInfoDTO getHotelInfoById(Long hotelId) {
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID: "+ hotelId));

        List<RoomDTO> rooms = hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDTO.class))
                .toList();

        return  new HotelInfoDTO(modelMapper.map(hotel, HotelDTO.class), rooms);
    }

    @Override
    public @Nullable List<HotelDTO> getAllHotels() {
        User user = getCurrentUser();
        log.info("Getting all hotels for the admin user with ID: {}", user.getId());
        List<Hotel> hotels = hotelRepository.findByOwner(user);
        return hotels
                .stream()
                .map((element) -> modelMapper.map(element, HotelDTO.class))
                .collect(Collectors.toList());
    }
}
