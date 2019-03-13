package com.blueharvest.demo.service.entity;

import com.blueharvest.demo.model.User;

public interface UserService {

    User getUserById(Long id);
    User findByUsername(String username);
    User saveUser(User user);
    User updateUser(User user);

}
