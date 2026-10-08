package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

@TableName("t_message")
public class Message{
    @TableId(type=IdType.AUTO)
    private Long id;
    private String topic;
    private String payload;
    private LocalDateTime recvTime;

    public Message(){

    }
    public Message(String topic,String payload,LocalDateTime recvTime){
        this.topic=topic;
        this.payload=payload;
        this.recvTime=recvTime;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getTopic(){
        return topic;
    }
    public void setTopic(String topic){
        this.topic=topic;
    }
    public String getPayload(){
        return payload;
    }
    public void setPayload(String payload){
        this.payload=payload;
    }

    public LocalDateTime getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(LocalDateTime recvTime) {
        this.recvTime = recvTime;
    }
}


