package com.periodical.trots.services;

import com.periodical.trots.entities.ReceiptEntity;
import com.periodical.trots.entities.StatusEntity;
import com.periodical.trots.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public List<ReceiptEntity> getAllReceiptForAdmin(){
        return receiptRepository.findAll(Sort.by(Sort.Direction.ASC, "status"));
    }

    public void acceptOrderByAdmin(Integer receiptId, StatusEntity status){
        ReceiptEntity receipt = receiptRepository.getById(receiptId);
        receipt.setStatus(status);
        receiptRepository.save(receipt);
    }

    public void discardOrderByAdmin(Integer receiptId, StatusEntity status){
        ReceiptEntity receipt = receiptRepository.getById(receiptId);
        receipt.setStatus(status);
        receiptRepository.save(receipt);
    }

    public Integer saveReceipt(ReceiptEntity receipt){
        return receiptRepository.save(receipt).getId();
    }

    public ReceiptEntity getReceiptById(Integer receiptId){
        return receiptRepository.getById(receiptId);
    }
}
