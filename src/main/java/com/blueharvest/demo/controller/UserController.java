package com.blueharvest.demo.controller;

import com.blueharvest.demo.dto.UserPrimaryAccountDto;
import com.blueharvest.demo.mapping.UserConverter;
import com.blueharvest.demo.service.entity.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserConverter userConverter;

    @Inject
    public UserController(UserService userService, UserConverter userConverter){
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping("/{id}")
    public UserPrimaryAccountDto getUserNameAndAccount(@PathVariable Long id){
        return userConverter.convertToUserPrimaryAccountDto(userService.getUserById(id));
    }
}
