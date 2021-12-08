package com.periodical.trots.services;

import com.periodical.trots.entities.PeriodicalEntity;
import com.periodical.trots.repositories.PeriodicalRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
class PeriodicalServiceTest {

    @Autowired
    private PeriodicalService periodicalService;

    @MockBean
    private PeriodicalRepository periodicalRepository;

    @Test
    void getAllPeriodicalsTest() {
        List<PeriodicalEntity> list = periodicalService.getAllPeriodicals();

        Assert.assertNotNull(list);
    }

    @Test
    void deletePeriodicalTest() {
        PeriodicalEntity periodical = new PeriodicalEntity();
        periodical.setSellId(1);

        boolean deletePeriodical = periodicalService.deletePeriodical(periodical.getSellId());

        Assert.assertTrue(deletePeriodical);
    }

    @Test
    void addPeriodicalTest() {
        PeriodicalEntity periodical = new PeriodicalEntity();
        Mockito.doReturn(new PeriodicalEntity(1)).when(periodicalRepository).save(periodical);
        Integer result = periodicalService.addPeriodical(periodical);

        Assert.assertNotNull(result);
    }

    @Test
    void getPeriodicalByIdTest() {
        PeriodicalEntity periodical = new PeriodicalEntity();
        periodical.setSellId(1);

        Mockito.doReturn(new PeriodicalEntity()).when(periodicalRepository).getBySellId(1);

        periodical = periodicalService.getPeriodicalById(periodical.getSellId());

        Assert.assertNotNull(periodical);
    }

    @Test
    void updatePeriodicalTest() {
        PeriodicalEntity periodical = new PeriodicalEntity();
        periodical.setSellId(1);
        PeriodicalEntity periodicalForm = new PeriodicalEntity();

        Mockito.doReturn(new PeriodicalEntity()).when(periodicalRepository).getBySellId(1);

        boolean result = periodicalService.updatePeriodical(periodical.getSellId(), periodicalForm);

        Assert.assertTrue(result);

    }

    @Test
    void getPeriodicalByTitle(){
        PeriodicalEntity periodical = new PeriodicalEntity();
        periodical.setTitle("title");

        Mockito.doReturn(new PeriodicalEntity()).when(periodicalRepository).getByTitle(periodical.getTitle());

        periodical = periodicalService.getPeriodicalByTitle(periodical.getTitle());

        Assert.assertNotNull(periodical);
    }
}