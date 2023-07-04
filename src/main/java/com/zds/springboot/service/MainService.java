package com.zds.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.springboot.mapper.DetailMapper;
import com.zds.springboot.mapper.MainMapper;
import com.zds.springboot.model.Detail;
import com.zds.springboot.model.Main;
import com.zds.springboot.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService  extends ServiceImpl<MainMapper, Main> {
    public Main findById(String id){
        QueryWrapper<Main> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("main_id",id);
        List<Main> list = list(queryWrapper);
        return list.get(0);
    }
}
