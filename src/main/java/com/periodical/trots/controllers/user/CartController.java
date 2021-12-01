package com.periodical.trots.controllers.user;

import com.periodical.trots.entities.*;
import com.periodical.trots.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private StatusService statusService;

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private PeriodicalHasReceiptService periodicalHasReceiptService;

    @Autowired
    private PeriodicalService periodicalService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/order-all")
    public String orderAllFromCart(HttpServletRequest req, @RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("email") String email, @RequestParam("telephone") String telephone, @RequestParam("address") String address) {
        Integer id = (Integer) req.getSession().getAttribute("ID");
        Double actualBalance = (Double) req.getSession().getAttribute("BALANCE");
        Double totalPrice = (Double) req.getSession().getAttribute("totalPrice");
        List<Cart> cart_list = (List<Cart>) req.getSession().getAttribute("cart-list");
        if (cart_list != null && id != null && actualBalance > totalPrice) {
            UserEntity user = (UserEntity) req.getSession().getAttribute("USER");
            ReceiptEntity receiptEntity = new ReceiptEntity();
            StatusEntity status = statusService.getStatusById(1);
            receiptEntity.setStatus(status);
            receiptEntity.setAdress(address);
            receiptEntity.setTelephoneNumber(telephone);
            receiptEntity.setEmail(email);
            receiptEntity.setName(name);
            receiptEntity.setSurname(surname);
            receiptEntity.setUser(user);
            Integer receiptId = receiptService.saveReceipt(receiptEntity);
            ReceiptEntity receipt = receiptService.getReceiptById(receiptId);
            PeriodicalHasReceiptEntity periodicalHasReceipt = new PeriodicalHasReceiptEntity();
            for (Cart c : cart_list) {
                PeriodicalHasReceiptEntityId periodicalHasReceiptEntityId = new PeriodicalHasReceiptEntityId();
                periodicalHasReceipt.setmReceipt(receipt);
                periodicalHasReceipt.setPeriodical(c.getPeriodical());
                periodicalHasReceipt.setId(periodicalHasReceiptEntityId);
                BigDecimal pricePerMonth = c.getPeriodical().getPricePerMonth();
                Double fullPrice = pricePerMonth.doubleValue() * c.getMonths();
                periodicalHasReceipt.setPricePerMonth(BigDecimal.valueOf(fullPrice));
                periodicalHasReceipt.setNumberOfMonth(c.getMonths());

                periodicalHasReceiptService.saveOrder(periodicalHasReceipt);
            }
            actualBalance = actualBalance - totalPrice;
            userService.updateBalanceAfterPayment(id, actualBalance);
            req.getSession().setAttribute("BALANCE", actualBalance);
            cart_list.clear();
            return "redirect:/cart";
        } else {
            if (id == null) {
                return "redirect:/login";
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cartGetMethod(HttpServletRequest request, Model model) {
        DecimalFormat dcf = new DecimalFormat("#.##");
        request.getSession().setAttribute("decimalFormat", dcf);
        List<Cart> cart_list = (List<Cart>) request.getSession().getAttribute("cart-list");
        UserEntity user = (UserEntity) request.getSession().getAttribute("USER");
        double totalPrice = 0;
        if (cart_list != null) {
            model.addAttribute("cartPeriodical", cart_list);
            BigDecimal pricePer = null;
            for(Cart c : cart_list){
                pricePer = c.getPeriodical().getPricePerMonth();
                totalPrice = totalPrice + pricePer.doubleValue()*c.getMonths();
            }
            request.getSession().setAttribute("totalPrice", totalPrice);
        }
        model.addAttribute("user", user);
        return "CartPage";
    }

    @GetMapping("/inc-dec")
    private String incDec(@RequestParam("action") String action, @RequestParam("id") Integer id, HttpServletRequest request) {
        ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

        if (action != null && id >= 1) {
            if (action.equals("inc")) {
                for (Cart c : cart_list) {
                    if (c.getPeriodical().getSellId() == id) {
                        int month = c.getMonths();
                        month++;
                        c.setMonths(month);
                        BigDecimal price = c.getPeriodical().getPricePerMonth();
                        c.setTotalPrice(price.doubleValue() * c.getMonths());
                        return "redirect:/cart";
                    }
                }
            }
            if (action.equals("dec")) {
                for (Cart c : cart_list) {
                    if (c.getPeriodical().getSellId() == id && c.getMonths() > 1) {
                        int month = c.getMonths();
                        month--;
                        c.setMonths(month);
                        BigDecimal price = c.getPeriodical().getPricePerMonth();
                        c.setTotalPrice(price.doubleValue() * c.getMonths());
                        break;
                    }
                }
                return "redirect:/cart";
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/remove-record")
    public String removePeriodical(@RequestParam("id") Integer id, HttpServletRequest request) {
        if (id != null) {
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            if (cart_list != null) {
                for (Cart c : cart_list) {
                    if (c.getPeriodical().getSellId() == id) {
                        cart_list.remove(cart_list.indexOf(c));
                        break;
                    }
                }
                return "redirect:/cart";
            }
        }
        return "redirect:/cart";

    }

    @PostMapping("/add-cart")
    public String addPeriodicalToCart(HttpServletRequest request, @RequestParam("periodicalId") Integer periodicalId, @RequestParam("page") Integer page) {
        List<Cart> cartList = new ArrayList<>();

        Cart cartObject = new Cart();
        PeriodicalEntity periodical = periodicalService.getPeriodicalById(periodicalId);
        cartObject.setPeriodical(periodical);
        BigDecimal pricePerMonth = periodical.getPricePerMonth();
        Double totalPrice = pricePerMonth.doubleValue();
        cartObject.setTotalPrice(totalPrice);
        cartObject.setMonths(1);

        List<Cart> cart_list = (List<Cart>) request.getSession().getAttribute("cart-list");

        if (cart_list == null) {
            cartList.add(cartObject);
            request.getSession().setAttribute("cart-list", cartList);
            return "redirect:/shop?page="+page;
        } else {
            cartList = cart_list;
            boolean exist = false;
            for (Cart c : cartList) {
                if (c.getPeriodical().getSellId() == periodicalId) {
                    exist = true;
                }
            }
            if (!exist) {
                cartList.add(cartObject);
                return "redirect:/shop?page="+page;
            }
        }
        return "redirect:/shop?page="+page;
    }

    @PostMapping("/buy-now")
    public String buyNow(@RequestParam("periodicalId") Integer periodicalId, @RequestParam("page") Integer page, HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("USER");
        if (user == null) {
            return "redirect:/login";
        }
        ReceiptEntity receiptEntity = new ReceiptEntity();
        StatusEntity status = statusService.getStatusById(1);
        receiptEntity.setStatus(status);
        receiptEntity.setAdress(user.getAddress());
        receiptEntity.setTelephoneNumber(user.getTelephone());
        receiptEntity.setEmail(user.getEmail());
        receiptEntity.setName(user.getName());
        receiptEntity.setSurname(user.getSurname());
        receiptEntity.setUser(user);
        Integer receiptId = receiptService.saveReceipt(receiptEntity);

        PeriodicalHasReceiptEntity periodicalHasReceipt = new PeriodicalHasReceiptEntity();
        ReceiptEntity receipt = receiptService.getReceiptById(receiptId);
        PeriodicalEntity periodical = periodicalService.getPeriodicalById(periodicalId);
        PeriodicalHasReceiptEntityId periodicalHasReceiptEntityId = new PeriodicalHasReceiptEntityId();
        periodicalHasReceiptEntityId.setReceiptId(receiptId);
        periodicalHasReceiptEntityId.setPeriodicalSellId(periodicalId);
        periodicalHasReceipt.setmReceipt(receipt);
        periodicalHasReceipt.setPeriodical(periodical);
        periodicalHasReceipt.setId(periodicalHasReceiptEntityId);
        periodicalHasReceipt.setPricePerMonth(periodical.getPricePerMonth());
        periodicalHasReceipt.setNumberOfMonth(1);


        BigDecimal pricePerMonth = periodical.getPricePerMonth();
        Double totalPrice = pricePerMonth.doubleValue();
        Double actualBalance = (Double) request.getSession().getAttribute("BALANCE");
        actualBalance = actualBalance - totalPrice;
        userService.updateBalanceAfterPayment(periodicalId, actualBalance);
        request.getSession().setAttribute("BALANCE", actualBalance);

        periodicalHasReceiptService.saveOrder(periodicalHasReceipt);
        return "redirect:/shop?page="+page;
    }
}
