package com.periodical.trots.services;

import com.periodical.trots.entities.PeriodicalEntity;
import com.periodical.trots.repositories.PeriodicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodicalService {

    @Autowired
    private PeriodicalRepository periodicalRepository;

    public List<PeriodicalEntity> getAllPeriodicals() {
        return periodicalRepository.findAll();
    }

    public void deletePeriodical(Integer id){
        periodicalRepository.deleteById(id);
    }

    public Integer addPeriodical(PeriodicalEntity periodicalEntity){
        return periodicalRepository.save(periodicalEntity).getSellId();
    }

    public PeriodicalEntity getPeriodicalById(Integer id){
        return periodicalRepository.getBySellId(id);
    }
}
