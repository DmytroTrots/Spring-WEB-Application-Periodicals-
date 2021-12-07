package com.periodical.trots.controllers.user;

import com.periodical.trots.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BalanceController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/top-up")
    public String topUpBalance(){
        return "BalancePage";
    }

    @PostMapping("/top-up")
    public String topUpBalancePost(RedirectAttributes redirectAttributes, @RequestParam("balance") Double balance, HttpServletRequest request){
        Double currentBalance = (Double) request.getSession().getAttribute("BALANCE");
        Integer userId = (Integer) request.getSession().getAttribute("ID");
        Double updatedBalance = userService.topUpBalance(balance, currentBalance, userId);
        String lang = String.valueOf(LocaleContextHolder.getLocale());
        if (lang.equals("en_US") || lang.equals("en")) {
            redirectAttributes.addFlashAttribute("ex", "Balance updated");
        }else{
            redirectAttributes.addFlashAttribute("ex", "Баланс поповнено");
        }
        request.getSession().setAttribute("BALANCE", updatedBalance);
        return "redirect:/top-up";
    }
}
