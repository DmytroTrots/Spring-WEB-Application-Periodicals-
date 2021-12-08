package com.periodical.trots.services;

import com.periodical.trots.entities.PeriodicalEntity;
import com.periodical.trots.repositories.PeriodicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Periodical service.
 */
@Service
public class PeriodicalService {

    @Autowired
    private PeriodicalRepository periodicalRepository;

    /**
     * Gets all periodicals.
     *
     * @return the all periodicals
     */
    public List<PeriodicalEntity> getAllPeriodicals() {
        return periodicalRepository.findAll();
    }

    /**
     * Delete periodical boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deletePeriodical(Integer id){
        periodicalRepository.deleteById(id);
        return true;
    }

    /**
     * Add periodical integer.
     *
     * @param periodicalEntity the periodical entity
     * @return the integer
     */
    public Integer addPeriodical(PeriodicalEntity periodicalEntity){
        return periodicalRepository.save(periodicalEntity).getSellId();
    }

    /**
     * Get periodical by id periodical entity.
     *
     * @param id the id
     * @return the periodical entity
     */
    public PeriodicalEntity getPeriodicalById(Integer id){
        return periodicalRepository.getBySellId(id);
    }

    /**
     * Update periodical boolean.
     *
     * @param periodicalId   the periodical id
     * @param periodicalForm the periodical form
     * @return the boolean
     */
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

    /**
     * Get periodical by title periodical entity.
     *
     * @param title the title
     * @return the periodical entity
     */
    public PeriodicalEntity getPeriodicalByTitle(String title){
        return periodicalRepository.getByTitle(title);
    }
}
