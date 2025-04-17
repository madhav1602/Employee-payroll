package com.bridgelabz.employeepayroll.dto;

import org.springframework.http.HttpStatus;

public class ResponseDTO {
    private String message;
//    private Object data;
    private HttpStatus status;



    public ResponseDTO(String message, HttpStatus status) {
        this.message = message;
//        this.data = data;
        this.status=status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }



}
