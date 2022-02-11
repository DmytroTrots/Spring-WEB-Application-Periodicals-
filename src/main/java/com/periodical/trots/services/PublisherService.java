package com.periodical.trots.services;

import com.periodical.trots.entities.PublisherEntity;
import com.periodical.trots.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Publisher service.
 */
@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<PublisherEntity> findAll(){
        return publisherRepository.findAll();
    }

    /**
     * Save integer.
     *
     * @param publisherEntity the publisher entity
     * @return the integer
     */
    public Integer save(PublisherEntity publisherEntity){
        return publisherRepository.save(publisherEntity).getId();
    }
}
