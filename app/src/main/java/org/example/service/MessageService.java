package org.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.entity.Message;
import org.example.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageMapper messageMapper;
    public MessageService(MessageMapper messageMapper){
        this.messageMapper=messageMapper;
    }

    public List<Message>listAll(){
        QueryWrapper<Message>qw=new QueryWrapper<>();
        qw.orderByDesc("id");
        qw.last("LIMIT 50");
        return messageMapper.selectList(qw);
    }
}