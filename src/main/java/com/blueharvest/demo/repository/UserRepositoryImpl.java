package com.blueharvest.demo.repository;

import com.blueharvest.demo.data.UserData;
import com.blueharvest.demo.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private UserData userData;

    public UserRepositoryImpl(){
        this.userData = UserData.getInstance();
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
