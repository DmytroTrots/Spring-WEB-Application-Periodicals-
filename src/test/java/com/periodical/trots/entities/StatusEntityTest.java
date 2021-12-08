package com.periodical.trots.entities;

import org.junit.Assert;
import org.junit.Test;

public class StatusEntityTest {

    @Test
    public void checkBeansTest(){
        StatusEntity status = new StatusEntity();
        status.setStatusName("name");

        Assert.assertEquals("name", status.getStatusName());
    }

}