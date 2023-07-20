package com.zds.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.springboot.mapper.TransferMapper;
import com.zds.springboot.model.transfer.Transfer;
import org.springframework.stereotype.Service;

@Service
public class TransferService extends ServiceImpl<TransferMapper, Transfer> {

}
