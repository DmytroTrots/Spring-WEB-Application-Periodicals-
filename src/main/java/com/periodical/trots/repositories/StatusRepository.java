package com.periodical.trots.repositories;

import com.periodical.trots.entities.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Status repository.
 */
@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {
}
