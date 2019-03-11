package com.blueharvest.demo.data;

import com.blueharvest.demo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserData {

    private volatile static UserData instance;
    private List<User> users;

    private UserData() {
        addUsersToTheInitialList();
    }

    public static UserData getInstance(){
        if(instance == null){
            synchronized (UserData.class){
                if(instance == null){
                    instance = new UserData();
                }
            }
        }

        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public User findUserById(Long id){
        Optional<User> userOptional = this.users.stream().filter(usr -> usr.getId().equals(id)).findFirst();
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
        List<Long> userAccounts = new ArrayList<>();
        userAccounts.add(1L);
        user1.setAccounts(userAccounts);
        this.users.add(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Hermione");
        user2.setSurname("Granger");
        this.users.add(user2);

        User user3 = new User();
        user3.setId(3L);
        user3.setName("Ronald");
        user3.setSurname("Weasley");
        this.users.add(user3);

        User user4 = new User();
        user4.setId(4L);
        user4.setName("Draco");
        user4.setSurname("Malfoy");
        this.users.add(user4);

        User user5 = new User();
        user5.setId(5L);
        user5.setName("Luna");
        user5.setSurname("Lovegood");
        this.users.add(user5);
    }

}
