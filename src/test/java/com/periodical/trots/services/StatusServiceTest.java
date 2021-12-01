package com.periodical.trots.services;

import com.periodical.trots.entities.StatusEntity;
import com.periodical.trots.repositories.StatusRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StatusServiceTest {

    @Autowired
    private StatusService statusService;

    @MockBean
    private StatusRepository statusRepository;

    @Test
    void getStatusByIdTest() {
        StatusEntity status = new StatusEntity();
        status.setId(1);
        Mockito.doReturn(new StatusEntity()).when(statusRepository).getById(1);

        status = statusService.getStatusById(status.getId());

        Assert.assertNotNull(status);
    }
}