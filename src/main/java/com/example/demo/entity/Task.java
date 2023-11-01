package com.example.demo.entity;

import java.time.LocalDate;

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
@Table(name = "task")
public class Task {
	
	// ID
	@Id
	@Column(name = "task_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskId;
	
	// タスク名
	@Column(name = "task_name")
	private String taskName;
	
	// タスクタグID（FK1）
	@ManyToOne
	private TaskTag taskTag;
	
	// 開始日
	@Column(name = "start_date")
	private LocalDate startDate;
	
	// 終了日
	@Column(name = "end_date")
	private LocalDate endDate;
	
	// メンバーID（FK2）
	@ManyToOne
	private Member member;
	
	// ステータスID（FK3）
	@ManyToOne
	private Status status;
	
	// プロジェクトID（FK4）
	@ManyToOne
	private Project project;
	
	// 議事録ID（FK5）
	@ManyToOne
	private Meeting meeting;
	
	// 備考
	@Column(name = "notes")
	private String notes;
}
