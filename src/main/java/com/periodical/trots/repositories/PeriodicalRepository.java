package com.periodical.trots.repositories;

import com.periodical.trots.entities.PeriodicalEntity;
import com.periodical.trots.entities.PublisherEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Periodical repository.
 */
@Repository
public interface PeriodicalRepository  extends JpaRepository<PeriodicalEntity, Integer> {
    /**
     * Update periodical by id.
     *
     * @param title                   the title
     * @param number                  the number
     * @param periodicityPerYear      the periodicity per year
     * @param percentageOfAdvertising the percentage of advertising
     * @param pricePerMonth           the price per month
     * @param description             the description
     * @param rating                  the rating
     * @param publisher               the publisher
     * @param images                  the images
     * @param sellId                  the sell id
     */
    @Modifying
    @Query("UPDATE PeriodicalEntity p SET p.title = :title, p.numberOfPages = :number, p.periodicityPerYear = :periodicityPerYear, p.percentageOfAdvertising = :percentageOfAdvertising, p.pricePerMonth = :pricePerMonth, p.description = :description, p.rating = :rating, p.publisher = :publisher, p.images = :images WHERE p.sellId = :sellId")
    void updatePeriodicalById(@Param("title") String title, @Param("number") Integer number, @Param("periodicityPerYear") Integer periodicityPerYear, @Param("percentageOfAdvertising") Integer percentageOfAdvertising, @Param("pricePerMonth") Double pricePerMonth, @Param("description") String description, @Param("rating") Double rating, @Param("publisher")PublisherEntity publisher, @Param("images") String images, @Param("sellId") Integer sellId);

    /**
     * Gets by sell id.
     *
     * @param periodicalId the periodical id
     * @return the by sell id
     */
    PeriodicalEntity getBySellId(Integer periodicalId);

    /**
     * Find all group by sell id page.
     *
     * @param pageable the pageable
     * @return the page
     */
    @Query("select p from PeriodicalEntity p group by p.sellId")
    Page<PeriodicalEntity> findAllGroupBySellId(Pageable pageable);

    /**
     * Gets by title.
     *
     * @param title the title
     * @return the by title
     */
    PeriodicalEntity getByTitle(String title);

}
