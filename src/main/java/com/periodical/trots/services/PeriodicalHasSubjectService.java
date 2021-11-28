package com.periodical.trots.services;

import com.periodical.trots.entities.PeriodicalHasSubjectEntity;
import com.periodical.trots.repositories.PeriodicalHasSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodicalHasSubjectService {

    @Autowired
    private PeriodicalHasSubjectRepository periodicalHasSubjectRepository;

    public void save(PeriodicalHasSubjectEntity periodicalHasSubject){
        periodicalHasSubjectRepository.save(periodicalHasSubject);
    }

}
