package com.grahamtech.ads.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.grahamtech.ads.daos.MyAdverseDrugEventDAO;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
@WebAppConfiguration
public class WebApplicationContextBaseControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private MyAdverseDrugEventDAO myAdverseDrugEventDAO;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
	    MediaType.APPLICATION_JSON.getType(),
	    MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private static final String BASE_CONTROLLER_URI = "/gt/get/drug/events/apikey/3";

    // Add WebApplicationContext field here.

    @Test
    public void getAdverseDrugEvents_apiKeyTest() throws Exception {
	    mockMvc.perform(
		get(BASE_CONTROLLER_URI).accept(
			    MediaType.parseMediaType(APPLICATION_JSON_UTF8
				    .toString()))).andExpect(status().isOk());
    }

    @Before
    public void setUp() {
	// Mockito.reset(myAdverseDrugEventDAOMock);
	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
		.build();
    }
}
