package com.blueharvest.demo.repository;

import com.blueharvest.demo.model.User;

public interface UserRepository{

    User findByUsername(String username);
    User findById(Long id);
    User save(User user);
    User update(User user);

}
