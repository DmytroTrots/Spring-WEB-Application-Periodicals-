package com.periodical.trots.services;

import com.periodical.trots.entities.PeriodicalHasReceiptEntity;
import com.periodical.trots.repositories.PeriodicalHasReceiptRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
class PeriodicalHasReceiptServiceTest {

    @Autowired
    private PeriodicalHasReceiptService periodicalHasReceiptService;

    @MockBean
    private PeriodicalHasReceiptRepository periodicalHasReceiptRepository;

    @Test
    void findAllReceiptOfUser() {
        List<PeriodicalHasReceiptEntity> list = periodicalHasReceiptService.findAllReceiptOfUser(1);

        Assert.assertNotNull(list);
    }

    @Test
    void saveOrder() {
        PeriodicalHasReceiptEntity periodicalHasReceiptEntity = new PeriodicalHasReceiptEntity();

        boolean result = periodicalHasReceiptService.saveOrder(periodicalHasReceiptEntity);

        Assert.assertTrue(result);
    }

    @Test
    void deleteOrdersAfterTimeTest(){
        boolean result = periodicalHasReceiptService.deleteOrdersAfterTime();

        Assert.assertTrue(result);
    }

}