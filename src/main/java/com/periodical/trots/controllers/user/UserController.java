package com.periodical.trots.controllers.user;

import com.periodical.trots.entities.UserEntity;
import com.periodical.trots.services.SecurityService;
import com.periodical.trots.services.UserService;
import com.periodical.trots.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private SecurityService securityService;


    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/shop?page=0";
        }
        model.addAttribute("userForm", new UserEntity());

        return "UserRegistrationPage";
    }

    @PostMapping("/registration")
    public String registration(RedirectAttributes redirectAttributes, @Valid @ModelAttribute("userForm") UserEntity userForm, Errors errors, BindingResult bindingResult) {
        String lang = String.valueOf(LocaleContextHolder.getLocale());
        if (errors.hasErrors()){
            return "UserRegistrationPage";
        }
        if (userService.findByUsername(userForm.getUsername())!=null){
            langEx(redirectAttributes, lang, "User with such username already exist", "Користувач з таким нікнеймом уже існує");
            return "redirect:/registration";
        }
        else if(userServiceImpl.findUserByEmail(userForm.getEmail())!=null){
            langEx(redirectAttributes, lang, "User with such email already exist", "Користувач з таким мейлом уже існує");
            return "redirect:/registration";
        } else if(userServiceImpl.findUserByTelephone(userForm.getTelephone())!=null){
            langEx(redirectAttributes, lang, "User with such telephone already exist", "Користувач з таким телефоном уже існує");
            return "redirect:/registration";
        }

        userService.save(userForm);

        langEx(redirectAttributes, lang, "User successfully registered", "Користувач успішно зареєстрований");

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model, String error) {
        if (securityService.isAuthenticated()) {
            return "redirect:/shop?page=0";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        return "LoginPage";
    }

    private void langEx(RedirectAttributes redirectAttributes, String lang, String s, String s2) {
        if (lang.equals("en_US") || lang.equals("en")) {
            redirectAttributes.addFlashAttribute("ex", s);
        } else {
            redirectAttributes.addFlashAttribute("ex", s2);
        }
    }

}
