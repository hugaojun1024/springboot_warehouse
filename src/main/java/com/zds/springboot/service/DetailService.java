package com.zds.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.springboot.mapper.DetailMapper;
import com.zds.springboot.model.Detail;
import com.zds.springboot.model.Main;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService extends ServiceImpl<DetailMapper, Detail> {

    public void saveList(List<Detail> detail) {
        for (Detail item:detail) {
            if (!(item.getMatName().equals("") || item.getMatName() == null)){
                save(item);
            }
        }
    }

    public List<Detail> findByMessageId(String messageId) {
        QueryWrapper<Detail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("message_id",messageId);
        return list(queryWrapper);
    }
}
