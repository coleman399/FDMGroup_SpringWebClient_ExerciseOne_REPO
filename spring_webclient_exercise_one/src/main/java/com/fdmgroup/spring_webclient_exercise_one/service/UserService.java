package com.fdmgroup.spring_webclient_exercise_one.service;

import java.util.List;

import com.fdmgroup.spring_webclient_exercise_one.client.UserWebClient;
import com.fdmgroup.spring_webclient_exercise_one.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserWebClient userClient;

    @Autowired
    public UserService(UserWebClient userClient) {
        this.userClient = userClient;
    }

    public List<User> getUsers() {
        return userClient.getUsers();
    }

    public User getUser(String username) {
        return userClient.getUser(username);
    }

    public User generateUser(User user) {
        return userClient.createUser(user);
    }

    public User amendUser(String username, User user) {
        return userClient.updateUser(user, username);
    }

    public void removeUser(String username) {
        userClient.deleteUser(username);
    }

}
