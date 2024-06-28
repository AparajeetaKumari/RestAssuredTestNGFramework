package com.qa.gorest.tests;

import com.qa.gorest.Restclient.RestClient;
import com.qa.gorest.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class AmadeusAPITest extends BaseTest {

    @Parameters({"baseuri","grantType","clientId","clientSecret"})
    @BeforeMethod
    public void setUp(String baseuri, String grantType,String clientId,String clientSecret){
        restClient = new RestClient(prop, baseuri);
        RestClient.generateToken("",grantType,clientId,clientSecret);
    }


}
