package com.periodical.trots.services;

import com.periodical.trots.entities.ReceiptEntity;
import com.periodical.trots.entities.StatusEntity;
import com.periodical.trots.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * The type Receipt service.
 */
@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    /**
     * Get all receipt for admin list.
     *
     * @return the list
     */
    public List<ReceiptEntity> getAllReceiptForAdmin(){
        return receiptRepository.findAll(Sort.by(Sort.Direction.ASC, "status"));
    }

    /**
     * Accept order by admin boolean.
     *
     * @param receiptId the receipt id
     * @param status    the status
     * @return the boolean
     */
    public boolean acceptOrderByAdmin(Integer receiptId, StatusEntity status){
        ReceiptEntity receipt = receiptRepository.getById(receiptId);
        receipt.setStatus(status);
        receiptRepository.save(receipt);
        return true;
    }

    /**
     * Discard order by admin boolean.
     *
     * @param receiptId the receipt id
     * @param status    the status
     * @return the boolean
     */
    public boolean discardOrderByAdmin(Integer receiptId, StatusEntity status){
        ReceiptEntity receipt = receiptRepository.getById(receiptId);
        receipt.setStatus(status);
        receiptRepository.save(receipt);
        return true;
    }

    /**
     * Save receipt integer.
     *
     * @param receipt the receipt
     * @return the integer
     */
    public Integer saveReceipt(ReceiptEntity receipt){
        return receiptRepository.save(receipt).getId();
    }

    /**
     * Get receipt by id receipt entity.
     *
     * @param receiptId the receipt id
     * @return the receipt entity
     */
    public ReceiptEntity getReceiptById(Integer receiptId){
        return receiptRepository.getById(receiptId);
    }

    /**
     * Get receipts for daily order list.
     *
     * @param createTime the create time
     * @return the list
     */
    public List<ReceiptEntity> getReceiptsForDailyOrder(Date createTime){
        return receiptRepository.findAllByStatusStatusNameAndCreateTime("accepted", createTime);
    }

}
