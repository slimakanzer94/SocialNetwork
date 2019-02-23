package com.example.demo.controllers;

import com.example.demo.entities.Message;
import com.example.demo.entities.User;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

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
    public String main(@AuthenticationPrincipal User user,Model model){
        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages",messages);
        model.addAttribute("user", user);
        return "main";
    }

    @PostMapping ("/main")
    public String addMessage (
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag){
        Message message = new Message(text,tag,user);
        messageRepository.save(message);
        return "redirect:/main";
    }

    @PostMapping ("/filter")
    public String addMessage (@RequestParam String filter,Model model){
        List<Message> messages;
        if (!filter.isEmpty() && filter!=null) messages = messageRepository.findByTag(filter);
        else messages=messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "/main";
    }
}
