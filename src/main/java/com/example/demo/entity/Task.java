package com.example.demo.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotBlank(message = "タスク名を入力してください")
	@Column(name = "task_name")
	private String taskName;
	
	// タスクタグID（FK1）
	@ManyToOne
	private TaskTag taskTag;
	
	// 開始日
	@NotNull(message = "開始日を入力してください")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "start_date")
	private LocalDate startDate;
	
	// 終了日
	@NotNull(message = "終了日を入力してください")
	@DateTimeFormat(iso = ISO.DATE)
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
	
	// 備考
	@Column(name = "notes")
	private String notes;
}
