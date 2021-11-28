package com.periodical.trots.services;

import com.periodical.trots.entities.StatusEntity;
import com.periodical.trots.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public StatusEntity getStatusById(Integer statusId){
        return statusRepository.getById(statusId);
    }
}
