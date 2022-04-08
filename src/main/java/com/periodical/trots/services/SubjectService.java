package com.periodical.trots.services;

import com.periodical.trots.entities.SubjectEntity;
import com.periodical.trots.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<SubjectEntity> findAll(){
        return subjectRepository.findAll();
    }

    public Integer save(SubjectEntity subjectEntity){
        return subjectRepository.save(subjectEntity).getId();
    }
}
