package com.qa.gorest.utils;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;


public class JsonPathValidator {

    public <T> T read(Response response,String jsonPath){
        String jasonResponse = response.getBody().asString();
        return JsonPath.read(jasonResponse,jsonPath);


    }

    public <T> List<T> readList(Response response, String jsonPath){
        String jasonResponse = response.getBody().asString();
        return JsonPath.read(jasonResponse,jsonPath);


    }

    public <T> List<Map<String,T>> readListOfMap(Response response, String jsonPath){
        String jasonResponse = response.getBody().asString();
        return JsonPath.read(jasonResponse,jsonPath);


    }
}
