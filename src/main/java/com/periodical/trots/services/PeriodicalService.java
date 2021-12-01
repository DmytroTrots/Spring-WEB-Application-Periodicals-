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

    public boolean deletePeriodical(Integer id){
        periodicalRepository.deleteById(id);
        return true;
    }

    public Integer addPeriodical(PeriodicalEntity periodicalEntity){
        return periodicalRepository.save(periodicalEntity).getSellId();
    }

    public PeriodicalEntity getPeriodicalById(Integer id){
        return periodicalRepository.getBySellId(id);
    }

    public boolean updatePeriodical(Integer periodicalId, PeriodicalEntity periodicalForm) {
        PeriodicalEntity periodical = periodicalRepository.getBySellId(periodicalId);
        periodical.setPricePerMonth(periodicalForm.getPricePerMonth());
        periodical.setPeriodicityPerYear(periodicalForm.getPeriodicityPerYear());
        periodical.setImages(periodicalForm.getImages());
        periodical.setPublisher(periodicalForm.getPublisher());
        periodical.setDescription(periodicalForm.getDescription());
        periodical.setTitle(periodicalForm.getTitle());
        periodical.setPercentageOfAdvertising(periodicalForm.getPercentageOfAdvertising());
        periodical.setNumberOfPages(periodicalForm.getNumberOfPages());
        periodical.setRating(periodicalForm.getRating());
        periodicalRepository.save(periodical);
        return true;
    }
}
