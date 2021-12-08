package com.periodical.trots.services;

import com.periodical.trots.entities.SubjectEntity;
import com.periodical.trots.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Subject service.
 */
@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<SubjectEntity> findAll(){
        return subjectRepository.findAll();
    }

    /**
     * Save integer.
     *
     * @param subjectEntity the subject entity
     * @return the integer
     */
    public Integer save(SubjectEntity subjectEntity){
        return subjectRepository.save(subjectEntity).getId();
    }
}
