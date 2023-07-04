package com.zds.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.springboot.mapper.MessageMapper;
import com.zds.springboot.mapper.WarningMapper;
import com.zds.springboot.model.Detail;
import com.zds.springboot.model.Message;
import com.zds.springboot.model.Warning;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarningService extends ServiceImpl<WarningMapper, Warning> {

    public void saveWarning(Warning warning) {
        if (warning.getWarningId() == null || warning.getWarningId().equals("")){
            //新设置预警值
            warning.setWarningId(IdWorker.getIdStr());
            //保存
            save(warning);
        }
        else{
            //修改预警值
            updateById(warning);
        }
    }

    public Integer getWarningByBatchNo(String batchNo) {
        QueryWrapper<Warning> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("batch_no",batchNo);
        List<Warning> list = list(queryWrapper);
        if (list.size() > 0){
            return list.get(0).getWarningNum();
        }
        else{
            return null;
        }
    }
}
