package com.blueharvest.demo.data;

import com.blueharvest.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserData {

    private List<User> users;

    private UserData() {
        addUsersToTheInitialList();
    }

    public List<User> getUsers() {
        return users;
    }

    public User findUserById(Long id){
        Optional<User> userOptional = this.users.stream().filter(usr -> usr.getId().equals(id)).findFirst();
        return userOptional.orElse(null);
    }

    public User findUserByUsername(String username){
        Optional<User> userOptional = this.users.stream().filter(usr -> usr.getUsername().equals(username)).findFirst();
        return userOptional.orElse(null);
    }

    public User addUser(User user){
        this.users.add(user);
        return user;
    }

    public void deleteUser(Long id){
        int userIndex = 0;
        for(int i = 0; i < this.users.size() ; i++){
            if(users.get(i).getId().equals(id)){
                userIndex = i;
            }
        }

        this.users.remove(userIndex);
    }

    public User updateUser(User userToBeUpdated){
        this.deleteUser(userToBeUpdated.getId());
        this.addUser(userToBeUpdated);
        return this.findUserById(userToBeUpdated.getId());
    }

    private void addUsersToTheInitialList(){
        this.users = new ArrayList<>();

        User user1 = new User();
        user1.setId(1L);
        user1.setName("Harry");
        user1.setSurname("Potter");
        user1.setUsername("harry");
        List<Long> userAccounts = new ArrayList<>();
        userAccounts.add(1L);
        user1.setAccounts(userAccounts);
        user1.setPassword("potter");
        this.users.add(user1);

    }

}
