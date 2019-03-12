package com.blueharvest.demo.repository;

import com.blueharvest.demo.data.UserData;
import com.blueharvest.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private UserData userData;

    @Inject
    public UserRepositoryImpl(UserData userData){
        this.userData = userData;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return userData.findUserById(id);
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return userData.updateUser(user);
    }
}
