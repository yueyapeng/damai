package com.damai.wine.service;

import com.damai.wine.Application;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Sylor Li
 * @date Apr 26, 2016  5:52:59 PM
 */
@RunWith(DefaultSpringJUnitRunner.class)
@SpringBootTest(classes= Application.class, webEnvironment=WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Commit
@WebAppConfiguration
public abstract class TestSupport {

    /**
     * 单测执行用户
     */
    static String TEST_ACCT = "yueyp";

    @Autowired
    public WebApplicationContext webApplicationContext;

    public MockMvc mockMvc;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
