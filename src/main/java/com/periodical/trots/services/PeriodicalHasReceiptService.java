package com.periodical.trots.services;

import com.periodical.trots.entities.PeriodicalHasReceiptEntity;
import com.periodical.trots.repositories.PeriodicalHasReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * The type Periodical has receipt service.
 */
@Service
public class PeriodicalHasReceiptService {

    @Autowired
    private PeriodicalHasReceiptRepository periodicalHasReceiptRepository;

    /**
     * Find all receipt of user list.
     *
     * @param userId the user id
     * @return the list
     */
    public List<PeriodicalHasReceiptEntity> findAllReceiptOfUser(Integer userId){
        return periodicalHasReceiptRepository.findAllOrdersOfUser(userId);
    }

    /**
     * Save order boolean.
     *
     * @param periodicalHasReceiptEntity the periodical has receipt entity
     * @return the boolean
     */
    public boolean saveOrder(PeriodicalHasReceiptEntity periodicalHasReceiptEntity){
        periodicalHasReceiptRepository.save(periodicalHasReceiptEntity);
        return true;
    }

    /**
     * Delete orders after time boolean.
     *
     * @return the boolean
     */
    public boolean deleteOrdersAfterTime(){
        List<PeriodicalHasReceiptEntity> list = periodicalHasReceiptRepository.findAll();
        Date date = new Date();
        long currentDate = date.getTime();
        for (PeriodicalHasReceiptEntity p : list){
            long endDate =p.getmReceipt().getCreateTime().getTime()+(((long) (12 / p.getPeriodical().getPeriodicityPerYear()) *p.getNumberOfMonth())*30L*24L*60L*60L*1000L);
            if (endDate<currentDate){
                periodicalHasReceiptRepository.delete(p);
            }
        }
        return true;
    }


}
