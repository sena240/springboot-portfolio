package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MeetingDocument;

public interface MeetingDocumentRepository extends JpaRepository<MeetingDocument, Long> {

}
