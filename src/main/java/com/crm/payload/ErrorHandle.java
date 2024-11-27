package com.crm.payload;

import java.util.Date;

public class ErrorHandle {
    private String message;
    private Date date;

    public String getRequest() {
        return request;
    }

    private String request;

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }



    public ErrorHandle(Date date, String message, String request) {

        this.date = date;
        this.message = message;
        this.request = request;
    }
}
