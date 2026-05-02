package com.AirBnb.projects.airbnb_app.service;

import com.AirBnb.projects.airbnb_app.dto.ProfileUpdateRequestDTO;
import com.AirBnb.projects.airbnb_app.dto.UserDTO;
import com.AirBnb.projects.airbnb_app.entity.User;
import com.AirBnb.projects.airbnb_app.exception.ResourceNotFoundException;
import com.AirBnb.projects.airbnb_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.AirBnb.projects.airbnb_app.util.AppUnits.getCurrentUser;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with ud: "+id));
    }

    @Override
    public void userProfile(ProfileUpdateRequestDTO profileUpdateRequestDTO) {
        User user = getCurrentUser();

        if(profileUpdateRequestDTO.getDateOfBirth() != null) user.setDateOfBirth(profileUpdateRequestDTO.getDateOfBirth());
        if(profileUpdateRequestDTO.getGender() != null) user.setGender(profileUpdateRequestDTO.getGender());
        if (profileUpdateRequestDTO.getName() != null) user.setName(profileUpdateRequestDTO.getName());

        userRepository.save(user);
    }

    @Override
    public UserDTO getMyProfile() {
        User user = getCurrentUser();
        log.info("Getting the profile for user with id: {}", user.getId());
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }
}
