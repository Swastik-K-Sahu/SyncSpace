package com.example.sync_space.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.sync_space.model.CollabDocument;

public interface DocumentRepository extends MongoRepository<CollabDocument, String> {
}
