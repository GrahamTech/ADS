package com.grahamtech.ads.controllers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.grahamtech.ads.daos.MyAdverseDrugEventDAO;
import com.grahamtech.ads.pojos.AdverseDrugEventResultFlattened;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
@TransactionConfiguration(defaultRollback = true)
public class HibernateIntegrationTest {

    @Autowired
    private MyAdverseDrugEventDAO myAdverseDrugEventDAO;

    @Before
    public void setUp() {
    }

    @Test
    @Transactional
    public void testInsert() {
	myAdverseDrugEventDAO.save(new AdverseDrugEventResultFlattened(
		"safetyreportid00001", "sender0001", new Long(10000)
			.longValue(), "companynumb00001",
		"patient reaction 1, patient reaction 2, patient reaction 3"));

	List<AdverseDrugEventResultFlattened> events = myAdverseDrugEventDAO
		.findEventBySafetyReportId("safetyreportid00001");
	assertNotNull(events);
	for (AdverseDrugEventResultFlattened event : events)
	    if (event.getEvent_id() < 1) {
	    fail();
	}
    }
}
