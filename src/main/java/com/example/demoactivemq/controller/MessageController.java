package com.example.demoactivemq.controller;

import com.example.demoactivemq.model.Item;
import com.example.demoactivemq.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send/{id}")
    public void send(@PathVariable("id") String id, @RequestBody List<Item> itemList) {
        messageService.send(id, itemList);
    }
}
