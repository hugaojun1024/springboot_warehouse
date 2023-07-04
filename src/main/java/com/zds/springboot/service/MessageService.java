package com.zds.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.springboot.mapper.MainMapper;
import com.zds.springboot.mapper.MessageMapper;
import com.zds.springboot.model.Main;
import com.zds.springboot.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends ServiceImpl<MessageMapper, Message> {
}
