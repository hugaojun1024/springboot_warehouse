package com.zds.springboot.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.zds.springboot.mapper.MessageMapper;
import com.zds.springboot.model.Detail;
import com.zds.springboot.model.Main;
import com.zds.springboot.model.Message;
import com.zds.springboot.utils.IDWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

    @Autowired
    private MainService mainService;

    @Autowired
    private DetailService detailService;

    @Autowired
    private MessageService messageService;
    public void saveMessage(Message message){
        // 生成id
        message.setMessageId(IdWorker.getIdStr());
        message.getMain().setMainId(IdWorker.getIdStr());
        List<Detail> detail = message.getDetail();
        for (Detail item : detail) {
            item.setDetailId(IdWorker.getIdStr());
            item.setMessageId(message.getMessageId());
        }
        message.setDetail(detail);
        System.out.println(message);

        // 保存
        detailService.saveList(detail);
        mainService.save(message.getMain());
        message.setMainId(message.getMain().getMainId());
        messageService.save(message);

    }

    public List<Message> findAll() {
        // 先查询所有message
        List<Message> list = messageService.list();
        for (Message msg: list) {
            Main main = mainService.findById(msg.getMainId());
            List<Detail> detailList = detailService.findByMessageId(msg.getMessageId());
            msg.setMain(main);
            msg.setDetail(detailList);
        }
        return list;
    }
}
