package com.periodical.trots.services;

import com.periodical.trots.entities.StatusEntity;
import com.periodical.trots.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Status service.
 */
@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    /**
     * Get status by id status entity.
     *
     * @param statusId the status id
     * @return the status entity
     */
    public StatusEntity getStatusById(Integer statusId){
        return statusRepository.getById(statusId);
    }
}
