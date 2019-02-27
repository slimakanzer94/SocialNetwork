package com.example.demo.controllers;

import com.example.demo.entities.Message;
import com.example.demo.entities.User;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
            @AuthenticationPrincipal User user, @Valid Message message, BindingResult bindingResult,Model model, @RequestParam("file")MultipartFile file) throws IOException {
        message.setAutor(user);
        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        }else {
            saveFile(message, file);
            model.addAttribute("message", null);
            messageRepository.save(message);
        }
        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        model.addAttribute("user", user);
        return "main";
    }

    private void saveFile(@Valid Message message, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            message.setFilename(resultFileName);
        }
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

    @GetMapping("/user-messages/{user}")
    public String userMessages (@AuthenticationPrincipal User currentUser, @PathVariable User user, Model model,
                                @RequestParam(required = false) Message message){
        Set<Message> messages = user.getMessages();

        model.addAttribute("messages", messages);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage (@AuthenticationPrincipal User currentUser, @PathVariable User user,
                                 @RequestParam("id") Message message,
                                 @RequestParam("text") String text,
                                 @RequestParam("tag") String tag,
                                 @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (message.getAutor().equals(currentUser)){
            if(!StringUtils.isEmpty(text)) message.setText(text);

            if (!StringUtils.isEmpty(tag))message.setTag(tag);

            saveFile(message, file);

            messageRepository.save(message);
        }

        return "redirect:/user-messages/"+user.getId();
    }
}
