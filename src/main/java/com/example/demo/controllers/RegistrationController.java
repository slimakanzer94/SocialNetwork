package com.example.demo.controllers;

import com.example.demo.UserRepository.UsersRepository;

import com.example.demo.entities.Role;
import com.example.demo.entities.Users;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UsersRepository usersRepository;
    @GetMapping("/registration")
    public String registrtion (){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String name, @RequestParam String password, Model model){
        Users user = new Users(name,password);
        Users userfromDb = (Users) usersRepository.findByUsername(user.getUsername());
        if (userfromDb !=null){
            model.addAttribute("msg", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        usersRepository.save(user);
        return "redirect:/login";
    }
}
