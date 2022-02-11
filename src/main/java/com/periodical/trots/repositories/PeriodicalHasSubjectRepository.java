package com.periodical.trots.repositories;

import com.periodical.trots.entities.PeriodicalHasSubjectEntity;
import com.periodical.trots.entities.PeriodicalHasSubjectEntityId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodicalHasSubjectRepository extends JpaRepository<PeriodicalHasSubjectEntity, Integer> {

    /**
     * Find all group by sell id page.
     *
     * @param pageable the pageable
     * @return the page
     */
    @Query("select p from PeriodicalHasSubjectEntity p group by p.periodical.sellId")
    Page<PeriodicalHasSubjectEntity> findAllGroupBySellId(Pageable pageable);

    /**
     * Find all by category page.
     *
     * @param pageable the pageable
     * @param category the category
     * @return the page
     */
    @Query("select p from PeriodicalHasSubjectEntity p where p.subject.themesOfBooks=:category")
    Page<PeriodicalHasSubjectEntity> findAllByCategory(Pageable pageable, @Param("category") String category);

    /**
     * Find all by category a and periodical title page.
     *
     * @param pageable the pageable
     * @param category the category
     * @param title    the title
     * @return the page
     */
    @Query("select p from PeriodicalHasSubjectEntity p where p.subject.themesOfBooks=:category and p.periodical.title like %:title%")
    Page<PeriodicalHasSubjectEntity> findAllByCategoryAAndPeriodicalTitle(Pageable pageable, @Param("category") String category, @Param("title") String title);

    /**
     * Find all by title page.
     *
     * @param pageable the pageable
     * @param title    the title
     * @return the page
     */
    @Query("select p from PeriodicalHasSubjectEntity p where p.periodical.title like %:title% group by p.periodical.sellId")
    Page<PeriodicalHasSubjectEntity> findAllByTitle(Pageable pageable, @Param("title") String title);

    /**
     * Gets all by periodical sell id.
     *
     * @param periodicalId the periodical id
     * @return the all by periodical sell id
     */
    List<PeriodicalHasSubjectEntity> getAllByPeriodicalSellId(Integer periodicalId);

    /**
     * Gets by subject.
     *
     * @param id the id
     * @return the by subject
     */
    PeriodicalHasSubjectEntity getBySubject(PeriodicalHasSubjectEntityId id);

}
