package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @GetMapping("/registration")
    public String registrtion (){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @Valid User user, BindingResult bindingResult,Model model, RedirectAttributes redirectAttributes){
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);

        if(isConfirmEmpty){
            model.addAttribute("password2Error", "Password confirmation cannot be empty");
        }

        if(user.getPassword()!=null && !user.getPassword().equals(passwordConfirm)){
            model.addAttribute("passwordError", "Passwords are different");
        }
        if(isConfirmEmpty||bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if (!userService.addUser(user)){
            model.addAttribute("msg", "This user already exist !");
            return "registration";
        }
        redirectAttributes.addFlashAttribute("msgType", "light-green");
        redirectAttributes.addFlashAttribute("msg", "User sucsesfully added");
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, RedirectAttributes redirectAttributes){
        boolean isActivated = userService.activateUser(code);
        if (isActivated){
            redirectAttributes.addFlashAttribute("msgType", "light-green");
             redirectAttributes.addFlashAttribute("msg", "User sucsesfully activated");
        } else {
            redirectAttributes.addFlashAttribute("msgType", "red");
            redirectAttributes.addFlashAttribute("msg", "Activation code is not found !");
        }
        return "redirect:/login";
    }
}
