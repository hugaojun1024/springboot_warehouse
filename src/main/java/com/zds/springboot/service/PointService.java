package com.zds.springboot.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.zds.springboot.model.Detail;
import com.zds.springboot.model.Main;
import com.zds.springboot.model.Message;
import com.zds.springboot.model.transfer.Transfer;
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

    @Autowired
    private TransferService transferService;

    @Autowired
    private TransferMainService transferMainService;

    @Autowired
    private TransferDetailService transferDetailService;

    /**
     * 保存接收的
     * @param message
     */
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

    /**
     * 保存接收的物资调拨信息
     * @param transfer
     */
    public void saveTransfer(Transfer transfer){
        transfer.setTransferId(IdWorker.getIdStr());
        transfer.getMain().setMainId(IdWorker.getIdStr());
        List<com.zds.springboot.model.transfer.Detail> detail = transfer.getDetail();
        for (com.zds.springboot.model.transfer.Detail item :detail) {
            item.setDetailId(IdWorker.getIdStr());
            item.setTransferId(transfer.getTransferId());
        }
        transfer.setDetail(detail);

        //保存到数据库
        transferDetailService.saveList(detail);
        transferMainService.save(transfer.getMain());
        transfer.setMainId(transfer.getMain().getMainId());
        transferService.save(transfer);

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

    public List<Transfer> findAllTransfer(){
        List<Transfer> list = transferService.list();
        for (Transfer trans: list) {
            com.zds.springboot.model.transfer.Main main = transferMainService.findById(trans.getMainId());
            List<com.zds.springboot.model.transfer.Detail> detailList = transferDetailService.findByTransferId(trans.getTransferId());
            trans.setMain(main);
            trans.setDetail(detailList);
        }
        return list;
    }

    public Message findById(String messageId, String mainId) {
        Message message = new Message();
        Main main  = mainService.findById(mainId);
        List<Detail> detailList = detailService.findByMessageId(messageId);
        message.setMain(main);
        message.setDetail(detailList);
        message.setMessageId(messageId);
        message.setMainId(mainId);
        return message;
    }
}
