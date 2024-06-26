package com.qa.gorest.tests;

import com.qa.gorest.Restclient.RestClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest {
    RestClient restClient;

    @Test
    public void getAllUsers(){
        restClient= new RestClient();
        restClient.get("/public/v2/users", true).then().assertThat().statusCode(200);
    }

    @Test
    public void getSpecificUsers(){
        restClient= new RestClient();
        Response res = restClient.get("/public/v2/users/6980138", true);
        JsonPath js = new JsonPath(res.getBody().asString());
        String id = js.getString("id");
        Assert.assertEquals(id,"6980138");
    }

    @Test
    public void getSpecificUsersUsingQueryParam(){
        restClient= new RestClient();
        Map<String,String> query = new HashMap<>();
        query.put("name","Fr. Mohini Khan");
        query.put("email","khan_mohini_fr@bergstrom.example");
        Response res = restClient.get("/public/v2/users/",null,query, true);
        JsonPath js = new JsonPath(res.getBody().asString());
        String id = js.getString("id");
        Assert.assertEquals(id,"6980131");
    }
}
