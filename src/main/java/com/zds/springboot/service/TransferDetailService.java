package com.zds.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.springboot.mapper.TransferDetailMapper;
import com.zds.springboot.model.transfer.Detail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferDetailService extends ServiceImpl<TransferDetailMapper, Detail> {
    public void saveList(List<Detail> detail) {
        for (Detail item:detail) {
            if (!(item.getMatName().equals("") || item.getMatName() == null)){
                save(item);
            }
        }
    }

    public List<Detail> findByTransferId(String transferId) {
        QueryWrapper<Detail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("transfer_id",transferId);
        return list(queryWrapper);
    }
}
