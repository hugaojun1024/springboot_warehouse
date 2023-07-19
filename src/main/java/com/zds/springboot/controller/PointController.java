package com.zds.springboot.controller;

import com.zds.springboot.common.Result;
import com.zds.springboot.config.websocket.WebSocket;
import com.zds.springboot.model.Message;
import com.zds.springboot.model.User;
import com.zds.springboot.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hugaojun Email:nat17185546@163.com
 * @create 2023-06-26-[上午 8:49]-周一
 */
@RestController
@RequestMapping("/point")
public class PointController {

//    @Autowired
//    private WebSocket webSocket;

    @Autowired
    private PointService pointService;

    @RequestMapping("/endpoint")
    public Result handlerWebsocket(@RequestBody Message message) {
        //下面做数据库保存
        pointService.saveMessage(message);
        // 使用字符串初始化JSONObject
        //JSONObject stringJsonObject = new JSONObject(jsonString);
        //System.out.println("JSONObject from String: " + stringJsonObject);
        //String stringJsonObject = "有新的订单";
        //发送webSocket消息

        WebSocket.sendMessage(message);
        return Result.success("已成功接收",null);
    }

    @RequestMapping("/getTest")
    public Result getTest(@RequestBody User user) {
        WebSocket.sendMessage(user);
        return Result.success("ok", null);
    }

    @RequestMapping("/get_messages")
    public Result getMessages(){
        List<Message> message = pointService.findAll();
        return Result.success("查询成功", message);
    }

    @RequestMapping("/get_messagesById")
    public Result getMessagesById(@RequestParam("messageId") String messageId, @RequestParam("mainId") String mainId) {
        Message message = pointService.findById(messageId, mainId);
        return Result.success("查询成功", message);
    }
}
