package com.periodical.trots.services;

import com.periodical.trots.entities.ReceiptEntity;
import com.periodical.trots.entities.StatusEntity;
import com.periodical.trots.repositories.ReceiptRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;

@SpringBootTest
class ReceiptServiceTest {

    @Autowired
    private ReceiptService receiptService;

    @MockBean
    private ReceiptRepository receiptRepository;

    @Test
    void getAllReceiptForAdminTest() {
        List<ReceiptEntity> list = receiptService.getAllReceiptForAdmin();

        Assert.assertNotNull(list);
    }

    @Test
    void acceptOrderByAdminTest() {
        StatusEntity statusEntity = new StatusEntity();
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setId(1);

        Mockito.doReturn(new ReceiptEntity(1)).when(receiptRepository).getById(1);

        boolean result = receiptService.acceptOrderByAdmin(receiptEntity.getId(), statusEntity);

        Assert.assertTrue(result);
    }

    @Test
    void discardOrderByAdminTest() {
        StatusEntity statusEntity = new StatusEntity();
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setId(1);

        Mockito.doReturn(new ReceiptEntity(1)).when(receiptRepository).getById(1);

        boolean result = receiptService.discardOrderByAdmin(receiptEntity.getId(), statusEntity);

        Assert.assertTrue(result);
    }

    @Test
    void saveReceiptTest() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        Mockito.doReturn(new ReceiptEntity(1)).when(receiptRepository).save(receiptEntity);
        Integer result = receiptService.saveReceipt(receiptEntity);

        Assert.assertNotNull(result);
    }

    @Test
    void getReceiptByIdTest() {
        ReceiptEntity receipt = new ReceiptEntity();
        receipt.setId(1);

        Mockito.doReturn(new ReceiptEntity()).when(receiptRepository).getById(1);

        receipt = receiptService.getReceiptById(receipt.getId());

        Assert.assertNotNull(receipt);
    }

    @Test
    void getReceiptsForDailyOrderTest() {
        Date date = new Date();
        List<ReceiptEntity> list = receiptService.getReceiptsForDailyOrder(date);

        Assert.assertNotNull(list);
    }
}