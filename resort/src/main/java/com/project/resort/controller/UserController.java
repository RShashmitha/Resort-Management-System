package com.project.resort.controller;

import com.project.resort.dto.UserDto;
import com.project.resort.dto.UserRequestDto;
import com.project.resort.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping("/createUser")
        public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
            UserDto createdUser = userService.createUser(userRequestDto);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }

        @GetMapping("/getById/{id}")
        public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
            UserDto userDto = userService.getUserById(id);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }

        @GetMapping("/getAllUsers")
        public ResponseEntity<List<UserDto>> getAllUsers() {
            List<UserDto> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        @PutMapping("/updateUser/{id}")
        public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDto userRequestDto) {
            UserDto updatedUser = userService.updateUser(id, userRequestDto);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }

        @DeleteMapping("/deleteUser/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
