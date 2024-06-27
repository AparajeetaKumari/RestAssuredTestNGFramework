package com.qa.gorest.base;

import com.qa.gorest.Restclient.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {
    protected ConfigurationManager configManager;
    protected Properties prop;
    protected RestClient restClient;

    @BeforeTest
    public void setUp(){
        configManager = new ConfigurationManager();
        prop = configManager.readInitConfig();
        String baseUrl = prop.getProperty("BASE_URI");
        System.out.println("baseUrl::"+baseUrl);
        restClient = new RestClient(prop, baseUrl);
    }
}
