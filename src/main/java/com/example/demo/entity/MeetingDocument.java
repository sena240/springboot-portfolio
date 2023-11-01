package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "meeting_document")
public class MeetingDocument {
	
	// ID
	@Id
	@Column(name = "meeting_document_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meetingDocumentId;
	
	// 議事録資料名
	@Column(name = "meeting_document_name")
	private String meetingDocumentName;
	
	// 議事録ID（FK）
	@ManyToOne
	private Meeting meeting;
	
	// 添付ファイル

}
