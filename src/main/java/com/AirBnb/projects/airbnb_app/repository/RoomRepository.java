package com.AirBnb.projects.airbnb_app.repository;

import com.AirBnb.projects.airbnb_app.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room , Long> {
}
