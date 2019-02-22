package com.example.demo.controllers;

import com.example.demo.UserRepository.UsersRepository;
import com.example.demo.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private UsersRepository usersRepository;

    @Value("${welcome.message}")
    private String message;

    @GetMapping("/")
    public String home1(Model model){
        model.addAttribute("message", message);

        return "home";
    }
    @GetMapping("/home")
    public String home2(Model model){
        model.addAttribute("message", message);

        return "home";
    }

    @GetMapping("/main")
    public String main(Model model){

        return "main";
    }

//    @PostMapping ("/addUser")
//    public String addUser (@RequestParam String name, @RequestParam String password){
//        Users user = new Users(name,password);
//        usersRepository.save(user);
//        return "redirect:/main";
//    }
}
