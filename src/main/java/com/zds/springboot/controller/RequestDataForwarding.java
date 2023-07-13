package com.zds.springboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zds.springboot.common.Result;
import com.zds.springboot.model.Warning;
import com.zds.springboot.service.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/request_data_forwarding")
public class RequestDataForwarding {

    @Autowired
    private WarningService warningService;

    /**
     * 库存信息查询
     * @param matName
     * @return
     */
    @RequestMapping("/stock_inquiry")
    public Object stockInquiry(@RequestParam(defaultValue = "") String matName){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.1.128.59:7002/ncpmsmm/receiveMessage";
        Class responseType = String.class;
        HttpHeaders head = new HttpHeaders();
        head.add("Content-Type","application/json");
        head.add("serviceId","MM_QLT_01");
        String body = "";
        if (!matName.equals("") && !(matName == null)){
            body = "{\n" +
                    "    \"matName\":\""+matName+"\"\n" +
                    "}\n";
        }
        HttpEntity<String> entity = new HttpEntity(body, head);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, responseType);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        return jsonObject;
    }

    /**
     * 库存信息查询
     * @param storageLocation
     * @return
     */
    @RequestMapping("/stock_inquiryBySL")
    public Object stockInquiryBySL(String storageLocation,
                                   Integer pageNum,
                                   Integer pageSize){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.1.128.59:7002/ncpmsmm/receiveMessage";
        Class responseType = String.class;
        HttpHeaders head = new HttpHeaders();
        head.add("Content-Type","application/json");
        head.add("serviceId","MM_QLT_01");
        String body = "";
        if (!storageLocation.equals("") && !(storageLocation == null)){
            body = "{\n" +
                    "    \"storageLocation\":\""+storageLocation+"\"\n" +
                    "}\n";
        }
        HttpEntity<String> entity = new HttpEntity(body, head);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, responseType);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        JSONArray objectData = jsonObject.getJSONArray("objectData");
        int total = objectData.size();
        JSONArray newData = new JSONArray();
        for (int i=(pageNum - 1)*pageSize;i<((pageNum - 1)*pageSize + pageSize);i++){
            JSONObject objectDataItem = objectData.getJSONObject(i);
            String batchNo = objectDataItem.getString("batchNo");
            //获取预警值
            Integer warningNum = warningService.getWarningByBatchNo(batchNo);
            objectDataItem.put("warningNum",warningNum);
            newData.add(objectDataItem);
        }
        jsonObject.put("objectData",newData);
        jsonObject.put("total",total);

        return jsonObject;
    }

    /**
     * 资产信息查询
     * @param unitCode
     * @return
     */
    @RequestMapping("/property_inquiry")
    public Object propertyInquiry(String unitCode){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.1.128.86:7001/nczcplat/receiveMessage";
        Class responseType = String.class;
        HttpHeaders head = new HttpHeaders();
        head.add("Content-Type","application/json");
        head.add("serviceId","AM_QLT_01");
        String body = "";
        if (!unitCode.equals("") && !(unitCode == null)){
            body = "{\n" +
                    "    \"unitCode\":\""+unitCode+"\"\n" +
                    "}\n";
        }
        HttpEntity<String> entity = new HttpEntity(body, head);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, responseType);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        return jsonObject;
    }

    @RequestMapping("/get_goods_all_life")
    public Result getGoodsAllLife(String batchNo){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.1.128.59:7002/ncpmsmm/receiveMessage";
        Class responseType = String.class;
        HttpHeaders head = new HttpHeaders();
        head.add("Content-Type","application/json");
        head.add("serviceId","GET_GOODS_ALL_LIFE");
        HttpEntity<String> entity = new HttpEntity("{\n" +
                "    \"batchNo\":\""+batchNo+"\"\n" +
                "}",head);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, responseType);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        return Result.success(jsonObject);
    }

    @RequestMapping("/get_goods")
    public Result getGoods(String warehouseNo, String matCode){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.1.1.211:7002/ncpmsmm/receiveMessage";
        Class responseType = String.class;
        HttpHeaders head = new HttpHeaders();
        head.add("Content-Type","application/json");
        head.add("serviceId","GET_GOODS");
        HttpEntity<String> entity = new HttpEntity("{\"warehouseNo\":\""+ warehouseNo +"\",\"matCode\":\""+ matCode +"\"}",head);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, responseType);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        return Result.success(jsonObject);
    }

    @RequestMapping("get_expend_goods")
    public Result getExpendGoods(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.1.128.59:7002/ncpmsmm/receiveMessage";
        Class responseType = String.class;
        HttpHeaders head = new HttpHeaders();
        head.add("Content-Type","application/json");
        head.add("serviceId","GET_EXPEND_GOODS");
        HttpEntity<String> entity = new HttpEntity("{\"warehouseNo\":\"02B030503\",\"matCode\":\"60000000035\"}",head);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, responseType);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        return Result.success(jsonObject);
    }

}
