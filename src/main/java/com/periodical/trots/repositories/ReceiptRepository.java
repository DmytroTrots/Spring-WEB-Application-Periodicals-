package com.periodical.trots.repositories;

import com.periodical.trots.entities.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * The interface Receipt repository.
 */
public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Integer> {
    /**
     * Find all by status status name and create time list.
     *
     * @param status_statusName the status status name
     * @param createTime        the create time
     * @return the list
     */
    List<ReceiptEntity> findAllByStatusStatusNameAndCreateTime(String status_statusName, Date createTime);
}
