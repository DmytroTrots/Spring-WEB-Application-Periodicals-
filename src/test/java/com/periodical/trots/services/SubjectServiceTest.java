package com.periodical.trots.services;

import com.periodical.trots.entities.SubjectEntity;
import com.periodical.trots.repositories.SubjectRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
class SubjectServiceTest {

    @Autowired
    private SubjectService subjectService;

    @MockBean
    private SubjectRepository subjectRepository;

    @Test
    void findAllTest() {
        List<SubjectEntity> list = subjectService.findAll();

        Assert.assertNotNull(list);

        Mockito.verify(subjectRepository, Mockito.times(1)).findAll();
    }

    @Test
    void saveTest() {
        SubjectEntity subjectEntity = new SubjectEntity();
        Mockito.doReturn(new SubjectEntity(1)).when(subjectRepository).save(subjectEntity);
        Integer result = subjectService.save(subjectEntity);

        Assert.assertNotNull(result);
    }
}