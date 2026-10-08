package org.example.controller;

import org.example.entity.Message;
import org.example.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;
    public MessageController(MessageService messageService){
        this.messageService=messageService;
    }

    @GetMapping
    public List<Message>list(){
        return messageService.listAll();
    }
}