package com.periodical.trots.entities;

import org.junit.Assert;
import org.junit.Test;


public class SubjectEntityTest {

    @Test
    public void checkBeansTest() {
        SubjectEntity subject = new SubjectEntity();
        subject.setThemesOfBooks("theme");

        Assert.assertEquals("theme", subject.getThemesOfBooks());
    }

}