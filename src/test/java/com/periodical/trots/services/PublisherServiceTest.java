package com.periodical.trots.services;

import com.periodical.trots.entities.PublisherEntity;
import com.periodical.trots.repositories.PublisherRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
class PublisherServiceTest {

    @Autowired
    private PublisherService publisherService;

    @MockBean
    private PublisherRepository publisherRepository;

    @Test
    void findAllTest() {
        List<PublisherEntity> list = publisherService.findAll();

        Assert.assertNotNull(list);
    }

    @Test
    void saveTest() {
        PublisherEntity publisherEntity = new PublisherEntity();
        Mockito.doReturn(new PublisherEntity(1)).when(publisherRepository).save(publisherEntity);
        Integer result = publisherService.save(publisherEntity);

        Assert.assertNotNull(result);
    }
}