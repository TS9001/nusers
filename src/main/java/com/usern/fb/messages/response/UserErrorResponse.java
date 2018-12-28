package com.usern.fb.messages.response;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class UserErrorResponse {
    private Date timestamp;
    private Integer status;
    private HttpStatus error;
    private String message;

    public UserErrorResponse(Date timestamp, HttpStatus error, int status, String message) {
        this.timestamp = timestamp;
        this.error = error;
        this.status = status;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getError() {
        return error;
    }

    public void setError(HttpStatus error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
