package com.example.gestion_employes.controller;

import com.example.gestion_employes.model.User;
import com.example.gestion_employes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // @GetMapping("/register")
    // public String registerForm(Model model) {
    //     model.addAttribute("user", new User());
    //     return "registration";
    // }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/login";
    }
}
