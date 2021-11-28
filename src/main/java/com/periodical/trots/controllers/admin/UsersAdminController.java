package com.periodical.trots.controllers.admin;

import com.periodical.trots.entities.UserEntity;
import com.periodical.trots.services.UserServiceImpl;
import com.periodical.trots.web.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersAdminController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/users")
    public String allUsersPageAdmin(Model model){
        model.addAttribute("allUsers", userService.getAll());
        model.addAttribute("userForm", new UserEntity());
        return "UsersPageForAdmin";
    }

    @PostMapping("/add-user")
    public String addUserByAdmin(@ModelAttribute("userForm") UserEntity userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "UsersPageForAdmin";
        }

        userService.saveUserByAdmin(userForm);

        return "redirect:/users";
    }

    @PostMapping("/ban-user")
    public String banUserByAdmin(@RequestParam("userId") Integer userId){
        userService.banUserById(userId);
        return "redirect:/users";
    }

    @PostMapping("/delete-user")
    public String deleteUserByAdmin(@RequestParam("userId") Integer userId){
        userService.deleteUserById(userId);
        return "redirect:/users";
    }
}
