package com.example.demoactivemq.service;

import com.example.demoactivemq.model.Item;
import com.example.demoactivemq.queue.Producer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    private Producer producer;

    public void send(String id, List<Item> itemList) {
        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("itemList", itemList);
        String json = new Gson().toJson(map);
        producer.sendMessage(json);
    }
}
