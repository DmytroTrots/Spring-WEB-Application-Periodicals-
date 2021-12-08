package com.periodical.trots.entities;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PublisherEntityTest {

    @Test
    public void checkBeansTest(){
        PublisherEntity publisher = new PublisherEntity();
        publisher.setId(1);
        publisher.setName("publisher");
        publisher.setTelephoneNumber("380963303303");

        Assert.assertEquals("publisher", publisher.getName());
        Assert.assertEquals("380963303303", publisher.getTelephoneNumber());
    }
}