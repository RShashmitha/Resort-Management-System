package com.project.resort.service;


import com.project.resort.dto.UserDto;
import com.project.resort.dto.UserRequestDto;
import com.project.resort.entity.User;
import com.project.resort.exception.NotFoundException;
import com.project.resort.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
    public class UserService {


        private final UserRepository userRepository;

        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }


        // Create a new user
        public UserDto createUser(UserRequestDto userRequestDto) {
            User user = convertDtoToEntity(userRequestDto);
            User savedUser = userRepository.save(user);
            return convertEntityToDto(savedUser);
        }

        // Get all users
        public List<UserDto> getAllUsers() {
            List<User> users = userRepository.findAll();
            List<UserDto> userDtos = new ArrayList<>();
            for (User user : users) {
                userDtos.add(convertEntityToDto(user));
            }
            return userDtos;
        }

        // Get user by ID
        public UserDto getUserById(Long id) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("User not found"));
            return convertEntityToDto(user);
        }

        // Update user
        public UserDto updateUser(Long id, UserRequestDto userRequestDto) {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("User not found"));

            existingUser.setUsername(userRequestDto.getUsername());
            existingUser.setEmail(userRequestDto.getEmail());
            existingUser.setPassword(userRequestDto.getPassword());
            existingUser.setPhonenumber(userRequestDto.getPhonenumber());
            existingUser.setRole(userRequestDto.getRole());

            User updatedUser = userRepository.save(existingUser);
            return convertEntityToDto(updatedUser);
        }

        // Delete user
        public void deleteUser(Long id) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("User not found"));
            userRepository.delete(user);
        }



    //    /    conversion of Dto to entity method
    private User convertDtoToEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setPhonenumber(userRequestDto.getPhonenumber());
        user.setRole(userRequestDto.getRole());
        return user;
    }

    // conversion of Entity to dto method
    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPhonenumber(user.getPhonenumber());
        userDto.setRole(user.getRole());
        return userDto;
    }
}


