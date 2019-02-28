package com.example.demo.controllers;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService userService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList (Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "users";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "useredit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("{user}/delete")
    public String deleteUser (@PathVariable User user, RedirectAttributes redirectAttributes){
        userService.delete(user);
        redirectAttributes.addFlashAttribute("dlt", " User "+user.getUsername()+" deleted sucsesfully !");
        return "redirect:/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String saveUser(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user, RedirectAttributes redirectAttributes){
        userService.saveUser(user, username, form);
        redirectAttributes.addFlashAttribute("msg", " User "+user.getUsername()+" saved sucsesfully !");
        return "redirect:/users";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile (@AuthenticationPrincipal User user, @RequestParam String password, @RequestParam String email, RedirectAttributes redirectAttributes){
        userService.updateProfile(user, password, email);
        redirectAttributes.addFlashAttribute("msg", "Profile updated sucsesfully");
        return "redirect:/profile";
    }

    @GetMapping("subscribe/{user}")
    public String subscribe(@AuthenticationPrincipal User currentUser,@PathVariable User user){
        userService.subscribe(currentUser,user);
        return "redirect:/user-messages/"+user.getId();
    }
    @GetMapping("unsubscribe/{user}")
    public String unSubscribe(@AuthenticationPrincipal User currentUser,@PathVariable User user){
        userService.unsubscribe(currentUser,user);
        return "redirect:/user-messages/"+user.getId();
    }
    @GetMapping("{type}/{user}/list")
    public String userList(Model model,@PathVariable User user, @PathVariable String type) {
        model.addAttribute("user", user);
        model.addAttribute("type", type);
        if ("subscriptions".equals(type))model.addAttribute("users", user.getSubscriptions());
        else model.addAttribute("users", user.getSubscribers());

        return "subscriptions";
    }
}
