package com.example.demoactivemq.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class QueueMessage {
    private String name;
    private List<Item> itemList;
}
