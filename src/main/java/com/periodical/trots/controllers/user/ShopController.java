package com.periodical.trots.controllers.user;

import com.periodical.trots.entities.*;
import com.periodical.trots.services.*;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ShopController {

    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private PeriodicalService periodicalService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    @RequestMapping("/shop")
    public String shopPage(Model model, HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("ID");

        if (userId != null) {
            UserEntity user = userService.findUserById(userId);
            BigDecimal balance = user.getBalance();
            Double actualBalance = balance.doubleValue();
            request.getSession().setAttribute("BALANCE", actualBalance);
        }

        model.addAttribute("periodicals", periodicalService.getAllPeriodicals());

        List<Cart> cartList = (List<Cart>) request.getSession().getAttribute("cart-list");

        if (cartList == null) {
            cartList = new ArrayList<>();
            request.getSession().setAttribute("cart_list", cartList);
        } else {
            request.getSession().setAttribute("cart_list", cartList);
        }

        return "ShopPage";
    }
}
