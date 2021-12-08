package com.periodical.trots.services;

import com.periodical.trots.entities.PeriodicalHasSubjectEntity;
import com.periodical.trots.repositories.PeriodicalHasSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * The type Periodical has subject service.
 */
@Service
public class PeriodicalHasSubjectService {

    @Autowired
    private PeriodicalHasSubjectRepository periodicalHasSubjectRepository;

    /**
     * Save boolean.
     *
     * @param periodicalHasSubject the periodical has subject
     * @return the boolean
     */
    public boolean save(PeriodicalHasSubjectEntity periodicalHasSubject) {
        periodicalHasSubjectRepository.save(periodicalHasSubject);
        return true;
    }

    /**
     * Find all with sort page.
     *
     * @param page  the page
     * @param sort  the sort
     * @param order the order
     * @return the page
     */
    public Page<PeriodicalHasSubjectEntity> findAllWithSort(Integer page, String sort, String order) {
        if (order.equals("ASC")) {
            return periodicalHasSubjectRepository.findAllGroupBySellId(PageRequest.of(page, 12, Sort.by(sort).ascending()));
        }
        return periodicalHasSubjectRepository.findAllGroupBySellId(PageRequest.of(page, 12, Sort.by(sort).descending()));
    }

    /**
     * Find all with category page.
     *
     * @param page     the page
     * @param sort     the sort
     * @param category the category
     * @param order    the order
     * @return the page
     */
    public Page<PeriodicalHasSubjectEntity> findAllWithCategory(Integer page, String sort, String category, String order) {
        if (order.equals("ASC")) {
            return periodicalHasSubjectRepository.findAllByCategory(PageRequest.of(page, 12, Sort.by(sort).ascending()), category);
        }
        return periodicalHasSubjectRepository.findAllByCategory(PageRequest.of(page, 12, Sort.by(sort).descending()), category);
    }

    /**
     * Find all with category and title page.
     *
     * @param page     the page
     * @param sort     the sort
     * @param category the category
     * @param title    the title
     * @param order    the order
     * @return the page
     */
    public Page<PeriodicalHasSubjectEntity> findAllWithCategoryAndTitle(Integer page, String sort, String category, String title, String order) {
        if (order.equals("ASC")) {
            return periodicalHasSubjectRepository.findAllByCategoryAAndPeriodicalTitle(PageRequest.of(page, 12, Sort.by(sort).ascending()), category, title);
        }
        return periodicalHasSubjectRepository.findAllByCategoryAAndPeriodicalTitle(PageRequest.of(page, 12, Sort.by(sort).descending()), category, title);
    }

    /**
     * Find all by title page.
     *
     * @param page  the page
     * @param sort  the sort
     * @param title the title
     * @param order the order
     * @return the page
     */
    public Page<PeriodicalHasSubjectEntity> findAllByTitle(Integer page, String sort, String title, String order) {
        if (order.equals("ASC")) {
            return periodicalHasSubjectRepository.findAllByTitle(PageRequest.of(page, 12, Sort.by(sort).ascending()), title);
        }
        return periodicalHasSubjectRepository.findAllByTitle(PageRequest.of(page, 12, Sort.by(sort).descending()), title);
    }

    /**
     * Find all by title page.
     *
     * @param page  the page
     * @param title the title
     * @return the page
     */
    public Page<PeriodicalHasSubjectEntity> findAllByTitle(Integer page, String title) {
        return periodicalHasSubjectRepository.findAllByTitle(PageRequest.of(page, 12), title);
    }

    /**
     * Find all with category and title page.
     *
     * @param page     the page
     * @param category the category
     * @param title    the title
     * @return the page
     */
    public Page<PeriodicalHasSubjectEntity> findAllWithCategoryAndTitle(Integer page, String category, String title) {
        return periodicalHasSubjectRepository.findAllByCategoryAAndPeriodicalTitle(PageRequest.of(page, 12), category, title);
    }

    /**
     * Find all with category page.
     *
     * @param page     the page
     * @param category the category
     * @return the page
     */
    public Page<PeriodicalHasSubjectEntity> findAllWithCategory(Integer page, String category) {
        return periodicalHasSubjectRepository.findAllByCategory(PageRequest.of(page, 12), category);
    }

    /**
     * Find all page.
     *
     * @param page the page
     * @return the page
     */
    public Page<PeriodicalHasSubjectEntity> findAll(Integer page) {
        return periodicalHasSubjectRepository.findAllGroupBySellId(PageRequest.of(page, 12));
    }


}
