package com.qa.gorest.tests;

import com.qa.gorest.Restclient.RestClient;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constant.APIHttpStatus;
import com.qa.gorest.listener.RetryAnalyzer;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest extends BaseTest {

    @BeforeMethod
    public void setUp(){
         restClient = new RestClient(prop,baseuri);
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void getAllUsers(){
        restClient.get("/public/v2/users", true).then().assertThat().statusCode(APIHttpStatus.OK_200.getCode());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void getSpecificUsers(){
        Response res = restClient.get("/public/v2/users/6988076", true);
        JsonPath js = new JsonPath(res.getBody().asString());
        String id = js.getString("id");
        Assert.assertEquals(id,"6988076");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void getSpecificUsersUsingQueryParam(){
        Map<String,String> query = new HashMap<>();
        query.put("name","Aparajeeta");
        query.put("email","Email0.10726465368655724@co.in");
        Response res = restClient.get("/public/v2/users/",null,query, true);
        JsonPath js = new JsonPath(res.getBody().asString());
        String id = js.getString("id");
        Assert.assertEquals(id,"6980131");
    }
}
