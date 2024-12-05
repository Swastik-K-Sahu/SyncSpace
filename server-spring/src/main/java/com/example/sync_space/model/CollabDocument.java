package com.example.sync_space.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "documents")
public class CollabDocument {
    
    @Id
    private String id;
    private String title;
    private String content;
    private List<String> contributors;
    public void setContent(Object content2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setContent'");
    }
    public String getContent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getContent'");
    }

    
}
