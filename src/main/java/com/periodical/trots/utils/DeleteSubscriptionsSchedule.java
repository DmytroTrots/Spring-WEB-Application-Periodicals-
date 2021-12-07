package com.periodical.trots.utils;

import com.periodical.trots.services.PeriodicalHasReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeleteSubscriptionsSchedule {

    @Autowired
    private PeriodicalHasReceiptService periodicalHasReceiptService;

    @Scheduled(cron = "00 00 22 1 * *", zone = "UTC")
    public void run() {
        periodicalHasReceiptService.deleteOrdersAfterTime();
    }
}
