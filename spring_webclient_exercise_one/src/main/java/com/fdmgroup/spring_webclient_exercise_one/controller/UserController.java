package com.fdmgroup.spring_webclient_exercise_one.controller;

import java.util.List;

import com.fdmgroup.spring_webclient_exercise_one.model.User;
import com.fdmgroup.spring_webclient_exercise_one.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String goToIndex(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/{username}")
    public String displayUser(@PathVariable("username") String username, Model model) {
        User user = userService.getUser(username);
        model.addAttribute("user", user);
        return "display-user";
    }

    @GetMapping("/createUser")
    public String goToCreateUserPage(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/createUser")
    public String createUser(User user) {
        userService.generateUser(user);
        return "redirect:/";
    }

    @GetMapping("/updateUser/{username}")
    public String goToUpdateUserPage(@PathVariable("username") String username, Model model) {
        User foundUser = userService.getUser(username);
        model.addAttribute("user", foundUser);
        return "update-user";
    }

    @PostMapping("/updateUser/{username}")
    public String updateUser(@PathVariable("username") String username, User user) {
        userService.amendUser(username, user);
        return "redirect:/";
    }

    @GetMapping("/deleteUser/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        userService.removeUser(username);
        return "redirect:/";
    }

}
