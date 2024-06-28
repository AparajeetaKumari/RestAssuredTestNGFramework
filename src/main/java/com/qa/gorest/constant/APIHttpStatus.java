package com.qa.gorest.constant;

public enum APIHttpStatus {
    OK_200(200,"OK"),
    CREATED_201(201,"Created"),
    NO_CONTENT_204(204,"No Content"),
    BAD_REQUEST_400(400,"BAD_REQUEST"),
    UNAUTHORIZED_401(401,"Unauthorized"),
    FORBIDDEN_403(403,"Forbidden"),
    NOT_FOUND_404(404,"Not Found"),
    INTERNAL_SERVER_ERROR_500(500,"Internal Server Error");

    private final int code;
    private final String errorMessage;

    APIHttpStatus(int code, String errorMessage){
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return errorMessage;
    }

    public String toString(){
        return  code+" "+ errorMessage;
    }
}
