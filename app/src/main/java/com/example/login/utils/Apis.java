package com.example.login.utils;

public class Apis {
    public static final String url_001="http://192.168.0.6:8080/api/";
    public static UserService getUserService(){
        return Cliente.getCliente(url_001).create(UserService.class);
    }
}
