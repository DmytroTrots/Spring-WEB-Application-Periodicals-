package com.periodical.trots.services;

import com.periodical.trots.entities.PeriodicalHasSubjectEntity;
import com.periodical.trots.repositories.PeriodicalHasSubjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PeriodicalHasSubjectServiceTest {

    @InjectMocks
    private PeriodicalHasSubjectService testInstance;

    @Mock
    private PeriodicalHasSubjectRepository periodicalHasSubjectRepository;

    @Test
    public void shouldSaveSubjectOfPeriodical() {
        PeriodicalHasSubjectEntity periodicalHasSubject = mock(PeriodicalHasSubjectEntity.class);
        when(periodicalHasSubjectRepository.save(periodicalHasSubject)).thenReturn(periodicalHasSubject);

        boolean result = testInstance.save(periodicalHasSubject);

        assertTrue(result);
    }

}