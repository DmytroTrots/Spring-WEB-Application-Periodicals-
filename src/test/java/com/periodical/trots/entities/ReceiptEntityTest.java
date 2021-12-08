package com.periodical.trots.entities;


import org.junit.Assert;
import org.junit.Test;

public class ReceiptEntityTest {

    @Test
    public void checkBeansTest(){
        ReceiptEntity receipt = new ReceiptEntity();
        StatusEntity status = new StatusEntity();
        receipt.setStatus(status);
        UserEntity user = new UserEntity();
        receipt.setUser(user);
        receipt.setEmail("email");
        receipt.setTelephoneNumber("380963303303");
        receipt.setSurname("surname");
        receipt.setId(1);
        receipt.setName("name");

        Assert.assertEquals(status, receipt.getStatus());
        Assert.assertEquals(user, receipt.getUser());
        Assert.assertEquals("email", receipt.getEmail());
        Assert.assertEquals("380963303303", receipt.getTelephoneNumber());
        Assert.assertEquals("surname", receipt.getSurname());
        Assert.assertEquals("name", receipt.getName());
    }

}