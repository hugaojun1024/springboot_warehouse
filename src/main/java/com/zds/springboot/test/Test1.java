package com.zds.springboot.test;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.1.128.59:7002/ncpmsmm/receiveMessage";
        Class responseType = String.class;
        HttpHeaders head = new HttpHeaders();
        head.add("Content-Type","application/json");
        head.add("serviceId","GET_GOODS_ALL_LIFE");

        //设置请求体参数

        //设置请求头参数
        Map<String,String> map = new HashMap<>();
        map.put("clientId","8");

        HttpEntity<String> entity = new HttpEntity("{\n" +
                "    \"batchNo\":\"2020121340355\"\n" +
                "}",head);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, responseType,map);
        System.out.println(responseEntity.getHeaders());
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getStatusCodeValue());
        System.out.println(responseEntity.getBody());

    }
}
