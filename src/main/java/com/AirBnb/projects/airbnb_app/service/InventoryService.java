package com.AirBnb.projects.airbnb_app.service;

import com.AirBnb.projects.airbnb_app.entity.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);
}
