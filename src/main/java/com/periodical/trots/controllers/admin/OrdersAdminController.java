package com.periodical.trots.controllers.admin;

import com.periodical.trots.entities.PeriodicalHasReceiptEntity;
import com.periodical.trots.entities.ReceiptEntity;
import com.periodical.trots.services.PeriodicalHasReceiptService;
import com.periodical.trots.services.ReceiptService;
import com.periodical.trots.services.StatusService;
import com.periodical.trots.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrdersAdminController {

    @Autowired
    private PeriodicalHasReceiptService periodicalHasReceiptService;

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/orders")
    public String periodicalsPageForAdmin(Model model) {
        List<ReceiptEntity> list = receiptService.getAllReceiptForAdmin();

        BigDecimal priceStart;

        List<Double> prices = new ArrayList<>();

        for (int i = 0; i< list.size(); i++) {
            Double priceFinal = 0.0;
            List<PeriodicalHasReceiptEntity> listOfPerHasEnt = new ArrayList<>(list.get(i).getReceiptEntities());
            for (PeriodicalHasReceiptEntity r : listOfPerHasEnt){
                priceStart = r.getPricePerMonth();
                priceFinal = priceFinal +priceStart.doubleValue();
            }
            prices.add(priceFinal);
        }
        model.addAttribute("prices", prices);
        model.addAttribute("receipt", list);
        return "OrdersPageForAdmin";
    }

    @PostMapping("/accept-order")
    public String acceptOrderByAdmin(RedirectAttributes redirectAttributes, @RequestParam("receiptId") Integer receiptId) {
        receiptService.acceptOrderByAdmin(receiptId, statusService.getStatusById(3));
        langEx(redirectAttributes, "Order was discarded", "Замовлення було відхилено");
        return "redirect:/orders";
    }

    @PostMapping("/discard-order")
    public String discardOrderByAdmin(RedirectAttributes redirectAttributes, @RequestParam("receiptId") Integer receiptId) {
        ReceiptEntity receipt = receiptService.getReceiptById(receiptId);
        BigDecimal balanceOfUser = receipt.getUser().getBalance();
        Double actualBalance = balanceOfUser.doubleValue();
        List<PeriodicalHasReceiptEntity> listOfPerHasEnt = new ArrayList<>(receipt.getReceiptEntities());
        BigDecimal priceStart;
        Double priceFinal = 0.0;
        for (PeriodicalHasReceiptEntity r : listOfPerHasEnt){
            priceStart = r.getPricePerMonth();
            priceFinal = priceFinal +priceStart.doubleValue();
        }
        actualBalance = actualBalance + priceFinal;
        userService.updateBalanceAfterPayment(receipt.getUser().getUsername(), actualBalance);

        receiptService.discardOrderByAdmin(receiptId, statusService.getStatusById(2));

        langEx(redirectAttributes, "Order was discarded", "Замовлення було відхилено");
        return "redirect:/orders";
    }

    private void langEx(RedirectAttributes redirectAttributes, String s, String s2) {
        String lang = String.valueOf(LocaleContextHolder.getLocale());
        if (lang.equals("en_US") || lang.equals("en")) {
            redirectAttributes.addFlashAttribute("ex", s);
        } else {
            redirectAttributes.addFlashAttribute("ex", s2);
        }
    }
}
