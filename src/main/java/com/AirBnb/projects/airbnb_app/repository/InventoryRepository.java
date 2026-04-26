package com.AirBnb.projects.airbnb_app.repository;

import com.AirBnb.projects.airbnb_app.dto.HotelPriceDTO;
import com.AirBnb.projects.airbnb_app.entity.Hotel;
import com.AirBnb.projects.airbnb_app.entity.Inventory;
import com.AirBnb.projects.airbnb_app.entity.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory , Long> {

    void deleteByDateAfterAndRoom(LocalDate date,Room room);

    @Query("""
           SELECT DISTINCT i.hotel    
           FROM Inventory i
           WHERE i.city = :city
                AND i.date BETWEEN :startDate AND :endDate
                AND i.closed = false
                AND (i.totalCount - i.bookedCount - i.reservedCount) >= :roomCount 
           GROUP BY i.hotel, i.room
           HAVING COUNT(i.date) = :dateCount
           """)

     Page<HotelPriceDTO> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startData,
            @Param("endDate") LocalDate endDate,
            @Param("roomCount") Integer roomCount,
            @Param("dateCount") Long dataCount,
            Pageable pageable
            );

    @Query("""
        SELECT i FROM Inventory i 
        WHERE i.room.id = :roomId 
        AND i.date BETWEEN :startDate AND :endDate 
        AND i.closed = false 
        AND (i.totalCount - i.bookedCount - i.reservedCount) >= :roomCount
    """)


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Inventory> findAndLockAvailableInventory(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startData,
            @Param("endDate") LocalDate endDate,
            @Param("roomCount") Integer roomCount
    );

    List<Inventory> findByHotelAndDateBetween(Hotel hotel, LocalDate startDate, LocalDate endDate);
}
