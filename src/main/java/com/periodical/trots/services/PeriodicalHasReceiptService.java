package com.periodical.trots.services;

import com.periodical.trots.entities.PeriodicalHasReceiptEntity;
import com.periodical.trots.repositories.PeriodicalHasReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodicalHasReceiptService {

    @Autowired
    private PeriodicalHasReceiptRepository periodicalHasReceiptRepository;

    public List<PeriodicalHasReceiptEntity> findAllReceipt(){
        return periodicalHasReceiptRepository.findAll();
    }

    public List<PeriodicalHasReceiptEntity> findAllReceiptOfUser(Integer userId){
        return periodicalHasReceiptRepository.findAllOrdersOfUser(userId);
    }

    public void saveOrder(PeriodicalHasReceiptEntity periodicalHasReceiptEntity){
        periodicalHasReceiptRepository.save(periodicalHasReceiptEntity);
    }


}
