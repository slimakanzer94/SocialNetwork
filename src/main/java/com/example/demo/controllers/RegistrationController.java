package com.example.demo.controllers;

import com.example.demo.repository.UserRepo;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    public String registrtion (){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String name, @RequestParam String password, Model model){
        User user = new User(name,password);
        User userfromDb = (User) userRepo.findByUsername(user.getUsername());
        if (userfromDb !=null){
            model.addAttribute("msg", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
