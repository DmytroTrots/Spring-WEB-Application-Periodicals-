package com.periodical.trots.controllers.admin;

import com.periodical.trots.entities.PeriodicalEntity;
import com.periodical.trots.entities.PeriodicalHasReceiptEntity;
import com.periodical.trots.entities.ReceiptEntity;
import com.periodical.trots.entities.UserEntity;
import com.periodical.trots.services.PeriodicalHasReceiptService;
import com.periodical.trots.services.ReceiptService;
import com.periodical.trots.services.StatusService;
import com.periodical.trots.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
                priceFinal = priceFinal +priceStart.doubleValue()*r.getNumberOfMonth();
            }
            prices.add(priceFinal);
        }
        model.addAttribute("prices", prices);
        model.addAttribute("receipt", list);
        return "OrdersPageForAdmin";
    }

    @PostMapping("/accept-order")
    public String acceptOrderByAdmin(@RequestParam("receiptId") Integer receiptId) {
        receiptService.acceptOrderByAdmin(receiptId, statusService.getStatusById(3));
        return "redirect:/orders";
    }

    @PostMapping("/discard-order")
    public String discardOrderByAdmin(@RequestParam("receiptId") Integer receiptId) {
        ReceiptEntity receipt = receiptService.getReceiptById(receiptId);
        BigDecimal balanceOfUser = receipt.getUser().getBalance();
        Double actualBalance = balanceOfUser.doubleValue();
        List<PeriodicalHasReceiptEntity> listOfPerHasEnt = new ArrayList<>(receipt.getReceiptEntities());
        BigDecimal priceStart;
        Double priceFinal = 0.0;
        for (PeriodicalHasReceiptEntity r : listOfPerHasEnt){
            priceStart = r.getPricePerMonth();
            priceFinal = priceFinal +priceStart.doubleValue()*r.getNumberOfMonth();
        }
        actualBalance = actualBalance + priceFinal;
        userService.updateBalanceAfterPayment(receipt.getUser().getId(), actualBalance);

        receiptService.discardOrderByAdmin(receiptId, statusService.getStatusById(2));
        return "redirect:/orders";
    }
}
