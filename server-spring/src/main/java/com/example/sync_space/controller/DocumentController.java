package com.example.sync_space.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.example.sync_space.model.CollabDocument;
import com.example.sync_space.model.DocumentEditMessage;
import com.example.sync_space.repository.DocumentRepository;
import com.example.sync_space.service.DocumentKafkaProducer;

@Controller
public class DocumentController {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentKafkaProducer kafkaProducer;

    @MessageMapping("/edit")
    @SendTo("/topic/updates")
    public CollabDocument updateDocument(DocumentEditMessage message) throws Exception {
        CollabDocument doc = documentRepository.findById(message.getId()).orElse(new CollabDocument());
        doc.setContent(message.getContent());
        documentRepository.save(doc);
        kafkaProducer.sendMessage(doc.getContent());
        return doc;
    }
}
