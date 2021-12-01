package com.periodical.trots.services;

import com.periodical.trots.entities.PeriodicalHasSubjectEntity;
import com.periodical.trots.repositories.PeriodicalHasSubjectRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class PeriodicalHasSubjectServiceTest {

    @Autowired
    private PeriodicalHasSubjectService periodicalHasSubjectService;

    @MockBean
    private PeriodicalHasSubjectRepository periodicalHasSubjectRepository;


    @Test
    void save() {
        PeriodicalHasSubjectEntity periodicalHasSubject = new PeriodicalHasSubjectEntity();

        boolean result = periodicalHasSubjectService.save(periodicalHasSubject);

        Assert.assertTrue(result);
    }

}