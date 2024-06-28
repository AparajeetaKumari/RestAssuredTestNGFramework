package com.qa.gorest.tests;

import com.qa.gorest.Restclient.RestClient;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constant.APIHttpStatus;
import com.qa.gorest.listener.RetryAnalyzer;
import com.qa.gorest.pojos.User;
import com.qa.gorest.utils.StringUtils;
import io.restassured.mapper.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseTest {

    @BeforeMethod
    public void setUp(){
         restClient = new RestClient(prop,baseuri);
    }

    @DataProvider
    public Object[][] createUserData(){
      return  new  Object[][]{
               {"Aiden","Male","Active"},
               {"Benhur","Male","Active"},
               {"Aparajeeta","Female","Active"}
       };
    }


    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "createUserData")
    public void createUserTest(String name, String gender,String status){
        User user = new User(name, StringUtils.randomEmail(), gender,status);
        Response res = restClient.post("/public/v2/users", "json",user,null,true);
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), APIHttpStatus.CREATED_201.getCode());
        JsonPath js = res.jsonPath();
        Integer id = js.getInt("id");


         res = restClient.get("/public/v2/users/"+id, true);
        Assert.assertEquals(id, res.getBody().jsonPath().getInt("id"));

    }
}
