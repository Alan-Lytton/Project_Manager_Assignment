package com.coding.projectmanager.controllers;

import com.coding.projectmanager.models.LoginUser;
import com.coding.projectmanager.models.User;
import com.coding.projectmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
     @Autowired
     private UserService userServ;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "login.jsp";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                           BindingResult result, Model model, HttpSession session) {
        userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "login.jsp";
        }else{
            session.setAttribute("UserId", newUser.getId());
            session.setAttribute("firstName", newUser.getFirstName());
            session.setAttribute("lastName", newUser.getLastName());
            return "redirect:/home";
        }
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
                        BindingResult result, Model model, HttpSession session) {
         User user = userServ.login(newLogin, result);
         if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "login.jsp";
         }else{
             session.setAttribute("UserId", user.getId());
             session.setAttribute("firstName", user.getFirstName());
             session.setAttribute("lastName", user.getLastName());
             return "redirect:/home";
         }
    }



}

