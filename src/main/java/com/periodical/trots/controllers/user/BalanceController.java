package com.periodical.trots.controllers.user;

import com.periodical.trots.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
public class BalanceController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/top-up")
    public String topUpBalance(){
        return "BalancePage";
    }

    @PostMapping("/top-up")
    public String topUpBalancePost(@RequestParam("balance") Double balance, HttpServletRequest request){
        BigDecimal currentBalance = (BigDecimal) request.getSession().getAttribute("BALANCE");
        Double doubleCurrentBalance = currentBalance.doubleValue();
        Integer userId = (Integer) request.getSession().getAttribute("ID");
        Double updatedBalance = userService.topUpBalance(balance, doubleCurrentBalance, userId);
        currentBalance = BigDecimal.valueOf(updatedBalance);
        request.getSession().setAttribute("BALANCE", currentBalance);
        return "redirect:/top-up";
    }
}
