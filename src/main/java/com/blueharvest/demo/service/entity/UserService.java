package com.blueharvest.demo.service.entity;

import com.blueharvest.demo.model.User;

public interface UserService {

    User getUserById(Long id);
    User saveUser(User user);
    User updateUser(User user);

}
