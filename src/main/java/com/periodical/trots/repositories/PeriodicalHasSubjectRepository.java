package com.periodical.trots.repositories;

import com.periodical.trots.entities.PeriodicalHasSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PeriodicalHasSubjectRepository extends JpaRepository<PeriodicalHasSubjectEntity, Integer> {
}
