package com.periodical.trots.repositories;

import com.periodical.trots.entities.PeriodicalHasReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeriodicalHasReceiptRepository extends JpaRepository<PeriodicalHasReceiptEntity, Integer> {
    @Query("select r from PeriodicalHasReceiptEntity r where r.mReceipt.user.id = :userId")
    List<PeriodicalHasReceiptEntity> findAllOrdersOfUser(@Param("userId") Integer userId);

}
