package com.AirBnb.projects.airbnb_app.service;

import com.AirBnb.projects.airbnb_app.dto.ProfileUpdateRequestDTO;
import com.AirBnb.projects.airbnb_app.dto.UserDTO;
import com.AirBnb.projects.airbnb_app.entity.User;
import org.jspecify.annotations.Nullable;

public interface UserService {
    User getUserById(Long id);

    void userProfile(ProfileUpdateRequestDTO profileUpdateRequestDTO);

    UserDTO  getMyProfile();
}
