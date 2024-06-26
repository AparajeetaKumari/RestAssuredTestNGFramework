package com.qa.gorest.Restclient;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.*;

import java.util.Map;

public class RestClient {

    private static RequestSpecBuilder specBuilder;
    private static String  BASE_URI="https://gorest.com";
    private static String  BEARER_TOKEN="https://gorest.com";

    public RestClient(){
         specBuilder = new RequestSpecBuilder();
    }

    public void addAuthHeader(){
        specBuilder.addHeader("Aurthorization","Bearer"+BEARER_TOKEN);
    }

    public void setContenttype(String contenttype){
        switch (contenttype.toLowerCase()){
            case "json" : specBuilder.setContentType(ContentType.JSON);
                          break;
            case "text" : specBuilder.setContentType(ContentType.TEXT);
                break;
            case "multipart" : specBuilder.setContentType(ContentType.MULTIPART);
                break;
            case "xml" : specBuilder.setContentType(ContentType.XML);
                break;
            default: System.out.println("Please pass right contenttype");

        }
    }

    private RequestSpecification createrequestSpecification(){
        specBuilder.setBaseUri(BASE_URI).setContentType(ContentType.JSON);
        addAuthHeader();
        return specBuilder.build();
    }

    private RequestSpecification createrequestSpecification(Map<String,String> header){
        specBuilder.setBaseUri(BASE_URI).setContentType(ContentType.JSON);
        addAuthHeader();
        if(header!=null){
            specBuilder.addHeaders(header);
        }
        return specBuilder.build();
    }

    private RequestSpecification createrequestSpecification(Map<String,String> header,Map<String,String> queryParam){
        specBuilder.setBaseUri(BASE_URI).setContentType(ContentType.JSON);
        addAuthHeader();
        if(header!=null){
            specBuilder.addHeaders(header);
        }
        if(queryParam!=null){
            specBuilder.addQueryParams(queryParam);
        }
        return specBuilder.build();
    }

    private RequestSpecification createrequestSpecification(Object requestBody,String contenttype,Map<String,String> header){
        specBuilder.setBaseUri(BASE_URI).setContentType(ContentType.JSON);
        addAuthHeader();
        if(requestBody!=null){
            specBuilder.setBody(requestBody);
        }
        if(contenttype!=null){
            setContenttype(contenttype);
        }
        if(header!=null){
            specBuilder.addHeaders(header);
        }

        return specBuilder.build();
    }

    private RequestSpecification createrequestSpecification(Object requestBody,String contenttype){
        specBuilder.setBaseUri(BASE_URI).setContentType(ContentType.JSON);
        addAuthHeader();
        if(requestBody!=null){
            specBuilder.setBody(requestBody);
        }
        if(contenttype!=null){
            setContenttype(contenttype);
        }

        return specBuilder.build();
    }


    //http methods
    public Response get(String serviceurl, boolean log){
        if(log){
          return   RestAssured.given().spec(createrequestSpecification()).log().all().when().get(serviceurl);
        }
       return  RestAssured.given().spec(createrequestSpecification()).when().get(serviceurl);
    }

    public Response get(String serviceurl,Map<String,String> headers, boolean log){
        if(log){
            return   RestAssured.given().spec(createrequestSpecification(headers)).log().all().when().get(serviceurl);
        }
        return  RestAssured.given().spec(createrequestSpecification(headers)).when().get(serviceurl);
    }

    public Response get(String serviceurl,Map<String,String> headers,Map<String,String> queryMap, boolean log){
        if(log){
            return   RestAssured.given().spec(createrequestSpecification(headers, queryMap)).log().all().when().get(serviceurl);
        }
        return  RestAssured.given().spec(createrequestSpecification(headers, queryMap)).when().get(serviceurl);
    }

    public Response post(String serviceUrl, String contentType, Object obj, Map<String, String> headers, boolean log){
        if(log){
           return  RestAssured.given().spec(createrequestSpecification(obj,contentType,headers)).log().all().when().post(serviceUrl);
        }

        return RestAssured.given().spec(createrequestSpecification(obj,contentType,headers)).when().post(serviceUrl);
    }

}
