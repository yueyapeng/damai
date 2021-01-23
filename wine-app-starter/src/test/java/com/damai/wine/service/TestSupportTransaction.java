package com.damai.wine.service;

import com.damai.wine.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sylor Li
 * @date Apr 26, 2016  5:52:59 PM
 */
@RunWith(DefaultSpringJUnitRunner.class)
@SpringBootTest(classes= Application.class, webEnvironment=WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
@WebAppConfiguration
public abstract class TestSupportTransaction{
	
}
