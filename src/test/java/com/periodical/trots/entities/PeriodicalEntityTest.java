package com.periodical.trots.entities;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PeriodicalEntityTest {

    @Test
    public void checkBeansTest(){
        PeriodicalEntity periodical = new PeriodicalEntity();
        periodical.setTitle("title");
        periodical.setRating(1.0);
        periodical.setDescription("description");
        periodical.setPeriodicityPerYear(1);
        periodical.setNumberOfPages(1);
        periodical.setPercentageOfAdvertising(1);
        periodical.setPricePerMonth(BigDecimal.valueOf(1.0));
        periodical.setSellId(1);
        PublisherEntity publisher = new PublisherEntity();
        periodical.setPublisher(publisher);
        periodical.setImages("image");

        Assert.assertEquals("title", periodical.getTitle());
        Assert.assertEquals("description", periodical.getDescription());
        Assert.assertEquals(1.0, periodical.getRating(), 0.1);
        Assert.assertEquals(publisher, periodical.getPublisher());
        Assert.assertEquals("image", periodical.getImages());
    }

}