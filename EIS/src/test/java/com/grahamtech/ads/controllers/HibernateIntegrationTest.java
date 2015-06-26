package com.grahamtech.ads.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.grahamtech.ads.daos.MyAdverseDrugEventDAO;
import com.grahamtech.ads.pojos.AdverseDrugEventResultFlattened;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
@Transactional
public class HibernateIntegrationTest {

    @Autowired
    private MyAdverseDrugEventDAO myAdverseDrugEventDAO;

    @Before
    public void setUp() {
    }

    @Test
    public void findByIdTest() {
	AdverseDrugEventResultFlattened event = myAdverseDrugEventDAO
		.findById(new Long(1));
	assertNotNull(event);
    }
}
