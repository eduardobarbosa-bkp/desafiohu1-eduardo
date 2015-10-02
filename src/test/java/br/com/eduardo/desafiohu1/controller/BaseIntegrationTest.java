package br.com.eduardo.desafiohu1.controller;

import br.com.eduardo.desafiohu1.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Eduardo on 01/10/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public abstract class BaseIntegrationTest{

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    public MockMvc getMockMvc() {
       if(mockMvc == null){
           mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
       }
        return mockMvc;
    }
}
