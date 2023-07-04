package com.zds.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.springboot.mapper.FeedBackMapper;
import com.zds.springboot.model.FeedBack;
import com.zds.springboot.utils.IDWorker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackService extends ServiceImpl<FeedBackMapper, FeedBack> {

    public boolean saveFeedBack(FeedBack feedBack) {
        if (feedBack.getFeedBackId() == null){
            // 生成id
            IDWorker idWorker = new IDWorker(1, 1, 1);
            feedBack.setFeedBackId(idWorker.nextStringId());
            //把数组换成字符串存储下来
            List<String> problemType = feedBack.getProblemType();
            String type = "";
            for (String s:problemType) {
                type = type + (s + ",");
            }
            type = type.substring(0,type.length() - 1);
            feedBack.setType(type);
            return save(feedBack);
        } else {
            return updateById(feedBack);
        }
    }
}
