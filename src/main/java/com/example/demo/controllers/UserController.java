package com.example.demo.controllers;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping
    public String userList (Model model){
        List<User> users = userRepo.findAll();
        model.addAttribute("users",users);
        return "users";
    }
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "useredit";
    }
}
