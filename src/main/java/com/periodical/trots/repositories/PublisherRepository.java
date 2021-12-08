package com.periodical.trots.repositories;

import com.periodical.trots.entities.PeriodicalEntity;
import com.periodical.trots.entities.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Publisher repository.
 */
@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Integer> {
}
