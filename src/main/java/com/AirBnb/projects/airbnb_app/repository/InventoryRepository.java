package com.AirBnb.projects.airbnb_app.repository;

import com.AirBnb.projects.airbnb_app.entity.Inventory;
import com.AirBnb.projects.airbnb_app.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory , Long> {

    void deleteByDateAfterAndRoom(LocalDate date, Room room);
}
