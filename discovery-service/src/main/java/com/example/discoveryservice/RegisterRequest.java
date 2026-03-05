package com.example.discoveryservice;

import lombok.Data;

@Data
public class RegisterRequest {

    private String serviceName;
    private String url;

}