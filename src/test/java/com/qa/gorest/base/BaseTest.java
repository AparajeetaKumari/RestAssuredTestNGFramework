package com.qa.gorest.base;

import com.qa.gorest.Restclient.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import io.qameta.allure.restassured.AllureRestAssured;

import java.util.Properties;

public class BaseTest {
    protected ConfigurationManager configManager;
    protected Properties prop;
    protected RestClient restClient;

    protected String baseuri;

    @Parameters({"baseuri"})
    @BeforeTest
    public void setUp(String baseuri){
        RestAssured.filters(new AllureRestAssured());

        configManager = new ConfigurationManager();
        prop = configManager.readInitConfig();
        restClient = new RestClient(prop, baseuri);
    }
}
