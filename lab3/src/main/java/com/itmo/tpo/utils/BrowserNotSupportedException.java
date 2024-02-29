package com.itmo.tpo.utils;

public class BrowserNotSupportedException extends RuntimeException{

    private String browser;

    BrowserNotSupportedException(String browser){
        this.browser = browser;
    }

    @Override
    public String getMessage() {
        return "Browser " + browser + " is not supported.";
    }
}
