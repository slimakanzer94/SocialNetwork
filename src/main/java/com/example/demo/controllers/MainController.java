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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String home1(Model model){

        return "home";
    }
    @GetMapping("/home")
    public String home2(Model model){
        return "home";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user,Model model){
        List<Message> messages=messageRepository.findAll();
        model.addAttribute("messages",messages);
        model.addAttribute("user", user);
        return "main";
    }

    @PostMapping ("/main")
    public String addMessage (
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, @RequestParam("file")MultipartFile file) throws IOException {
        Message message = new Message(text,tag,user);
        if (file!=null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File (uploadPath);
            if(!uploadDir.exists()) uploadDir.mkdir();
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath+"/"+resultFileName));
            message.setFilename(resultFileName);
        }
        messageRepository.save(message);
        return "redirect:/main";
    }

    @PostMapping ("/filter")
    public String addMessage (@AuthenticationPrincipal User user, @RequestParam String filter,Model model){
        List<Message> messages;
        if (!filter.isEmpty() && filter!=null) messages = messageRepository.findByTag(filter);
        else messages=messageRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("messages", messages);
        return "/main";
    }
}
