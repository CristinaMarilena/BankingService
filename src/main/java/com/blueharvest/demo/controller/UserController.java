package com.blueharvest.demo.controller;

import com.blueharvest.demo.dto.UserDto;
import com.blueharvest.demo.exception.NotFoundException;
import com.blueharvest.demo.converter.UserConverter;
import com.blueharvest.demo.model.User;
import com.blueharvest.demo.service.entity.UserService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserConverter userConverter;

    @Inject
    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping(value = "/{id}")
    public UserDto getUserInformation(@PathVariable Long id) {
        User user = userService.getUserById(id);

        if (user != null) {
            return userConverter.convertToUserDto(user);
        } else {
            throw new NotFoundException("No user provided with this id");
        }
    }

    @GetMapping(value = "/login")
    public UserDto getUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userService.findByUsername(username);

        if (user != null && password.equals(user.getPassword())) {
            return userConverter.convertToUserDto(user);
        } else {
            throw new NotFoundException("No user provided with this id");
        }
    }

    @PostMapping("/registration")
    public User saveUser(@Valid @RequestBody User user) {
        return this.userService.saveUser(user);
    }
}
