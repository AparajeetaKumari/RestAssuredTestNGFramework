package com.qa.gorest.tests;

import com.qa.gorest.Restclient.RestClient;
import com.qa.gorest.pojos.User;
import com.qa.gorest.utils.StringUtils;
import io.restassured.mapper.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserTest {
    RestClient restClient;

    @Test
    public void createUserTest(){
        restClient = new RestClient();
        User user = new User("Aparajeeta", StringUtils.randomEmail(), "female","active");
        Response res = restClient.post("/public/v2/users", "json",user,null,true);
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(),201);
        JsonPath js = res.jsonPath();
        Integer id = js.getInt("id");


        restClient = new RestClient();
         res = restClient.get("/public/v2/users/"+id, true);
        Assert.assertEquals(id, res.getBody().jsonPath().getInt("id"));

    }
}
