package com.myfinbank.customer.exceptions;

public class CustomerAlreadyExistsException extends Exception{
	String url;
    public CustomerAlreadyExistsException(String message,String url){
        super(message);
        this.url=url;
    }
    public String getUrl() {
        return url;
    }
}