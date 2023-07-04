package com.zds.springboot.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public class Http {

    public static HttpResponse<String> get(String url) throws UnirestException {
        return Unirest.get(url).asString();
    }

    public HttpResponse<String> post(String url, String body) throws UnirestException {
        return Unirest.post(url).body(body).asString();
    }

    public HttpResponse<String> put(String url, String body) throws UnirestException {
        return Unirest.put(url).body(body).asString();
    }

    public HttpResponse<String> delete(String url) throws UnirestException {
        return Unirest.delete(url).asString();
    }
}
