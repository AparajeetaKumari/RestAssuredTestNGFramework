package com.qa.gorest.tests;

import com.qa.gorest.Restclient.RestClient;
import com.qa.gorest.base.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest extends BaseTest {

    @Test
    public void getAllUsers(){
        restClient.get("/public/v2/users", true).then().assertThat().statusCode(200);
    }

    @Test
    public void getSpecificUsers(){
        Response res = restClient.get("/public/v2/users/6987116", true);
        JsonPath js = new JsonPath(res.getBody().asString());
        String id = js.getString("id");
        Assert.assertEquals(id,"6987116");
    }

    @Test
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
