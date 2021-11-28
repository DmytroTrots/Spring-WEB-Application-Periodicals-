package com.periodical.trots.repositories;

import com.periodical.trots.entities.ReceiptEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Integer> {
}
