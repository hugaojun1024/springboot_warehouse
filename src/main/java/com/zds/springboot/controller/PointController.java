package com.zds.springboot.controller;

import com.zds.springboot.common.Constants;
import com.zds.springboot.common.Result;
import com.zds.springboot.config.websocket.WebSocket;
import com.zds.springboot.model.Message;
import com.zds.springboot.model.User;
import com.zds.springboot.model.transfer.Transfer;
import com.zds.springboot.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    //    @RequestMapping("/endpoint")
//    public Result handlerWebsocket(@RequestBody Message message) {
//        //下面做数据库保存
//        pointService.saveMessage(message);
//        // 使用字符串初始化JSONObject
//        //JSONObject stringJsonObject = new JSONObject(jsonString);
//        //System.out.println("JSONObject from String: " + stringJsonObject);
//        //String stringJsonObject = "有新的订单";
//        //发送webSocket消息
//
//        WebSocket.sendMessage(message);
//        return Result.success("已成功接收",null);
//    }

    @RequestMapping("/endpoint")
    public Result handlerWebsocket(@RequestBody List<Message> msgs) {
        //List<Message> messages = null;
        //String unescapeString = org.apache.commons.lang3.StringEscapeUtils.unescapeJava(null);
//        try{
//            messages = JSONArray.parseArray(unescapeString, Message.class);
//        } catch (Exception e){
//            e.printStackTrace();
//            return Result.error(Constants.CODE_WRONG_SYSTEM,"发生错误，请检查推送数据格式是否正确！");
//        }
        if (msgs.size() == 0 || msgs.get(0).getMain().getPurcOrderId() == null || msgs.get(0).getMain().getPurcOrderId().equals("")) {
            return Result.error(Constants.CODE_WRONG_SYSTEM,"发生错误，请检查推送数据格式是否正确！");
        }
        //下面做数据库保存
        for (Message message: msgs) {
            pointService.saveMessage(message);
        }
        // 使用字符串初始化JSONObject
        //JSONObject stringJsonObject = new JSONObject(jsonString);
        //System.out.println("JSONObject from String: " + stringJsonObject);
        //String stringJsonObject = "有新的订单";
        //发送webSocket消息
        WebSocket.sendMessage(msgs);
        return Result.success("已成功接收",null);
    }

    @RequestMapping("/save_transfer")
    public Result saveTransfer(@RequestBody List<Transfer> transfers){
        if (transfers.size() == 0 || transfers.get(0).getMain().getTransferNo() == null || transfers.get(0).getMain().getTransferNo().equals("")) {
            return Result.error(Constants.CODE_WRONG_SYSTEM,"发生错误，请检查推送数据格式是否正确！");
        }
        //下面做数据库保存
        for (Transfer transfer: transfers) {
            pointService.saveTransfer(transfer);
        }
        return Result.success("已成功接收-物资调拨信息",null);
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

    @RequestMapping("/get_transfer")
    public Result getTransfer(){
        List<Transfer> allTransfer = pointService.findAllTransfer();
        return Result.success("查询成功",allTransfer);
    }

    @RequestMapping("/get_messagesById")
    public Result getMessagesById(@RequestParam("messageId") String messageId, @RequestParam("mainId") String mainId) {
        Message message = pointService.findById(messageId, mainId);
        return Result.success("查询成功", message);
    }

    @RequestMapping("/get_transferById")
    public Result getTransferById(@RequestParam("transferId") String transferId, @RequestParam("mainId") String mainId) {
        Transfer transfer = pointService.findTransferById(transferId, mainId);
        return Result.success("查询成功", transfer);
    }
}
