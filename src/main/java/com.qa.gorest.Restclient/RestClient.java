package com.qa.gorest.Restclient;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.*;

import java.util.Map;
import java.util.Properties;

public class RestClient {

    private static RequestSpecBuilder specBuilder;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    //private static String  BASE_URI="https://gorest.co.in";
    private static String  BEARER_TOKEN="7267c9d99618120d69624f04ab4e1c8263e4cfbd21ad56ef026d26fcb12aa74f";

    private Properties prop;
    private String baseurl;

    boolean isAuthAdded = false;

    public RestClient(Properties prop, String baseurl){
         specBuilder = new RequestSpecBuilder();
         this.prop = prop;
         this.baseurl = baseurl;
    }

    public void addAuthHeader(){
        if(!isAuthAdded) {
            specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("BEARER_TOKEN"));
            isAuthAdded = true;
        }
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
        specBuilder.setBaseUri(baseurl).setContentType(ContentType.JSON);
        addAuthHeader();
        return specBuilder.build();
    }

    private RequestSpecification createrequestSpecification(Map<String,String> header){
        specBuilder.setBaseUri(baseurl).setContentType(ContentType.JSON);
        addAuthHeader();
        if(header!=null){
            specBuilder.addHeaders(header);
        }
        return specBuilder.build();
    }

    private RequestSpecification createrequestSpecification(Map<String,String> header,Map<String,String> queryParam){
        specBuilder.setBaseUri(baseurl).setContentType(ContentType.JSON);
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
        specBuilder.setBaseUri(baseurl);
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
        specBuilder.setBaseUri(baseurl).setContentType(ContentType.JSON);
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

    public Response put(String serviceUrl, String contentType, Object obj, Map<String, String> headers, boolean log){
        if(log){
            return  RestAssured.given().spec(createrequestSpecification(obj,contentType,headers)).log().all().when().put(serviceUrl);
        }

        return RestAssured.given().spec(createrequestSpecification(obj,contentType,headers)).when().put(serviceUrl);
    }

    public Response patch(String serviceUrl, String contentType, Object obj, Map<String, String> headers, boolean log){
        if(log){
            return  RestAssured.given().spec(createrequestSpecification(obj,contentType,headers)).log().all().when().patch(serviceUrl);
        }

        return RestAssured.given().spec(createrequestSpecification(obj,contentType,headers)).when().patch(serviceUrl);
    }


    public Response delete(String serviceUrl,boolean log){
        if(log){
            return  RestAssured.given().spec(createrequestSpecification()).log().all().when().delete(serviceUrl);
        }

        return RestAssured.given().spec(createrequestSpecification()).when().delete(serviceUrl);
    }

}
