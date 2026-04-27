package com.AirBnb.projects.airbnb_app.util;

import com.AirBnb.projects.airbnb_app.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class AppUnits {

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
