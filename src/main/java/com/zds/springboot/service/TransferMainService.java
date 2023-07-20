package com.zds.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.springboot.mapper.TransferMainMapper;
import com.zds.springboot.model.transfer.Main;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferMainService extends ServiceImpl<TransferMainMapper, Main> {
    public Main findById(String mainId) {
        QueryWrapper<Main> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("main_id",mainId);
        List<Main> list = list(queryWrapper);
        return list.get(0);
    }
}
