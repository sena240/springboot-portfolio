package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "task_tag")
public class TaskTag {
	
	// ID
	@Id
	@Column(name = "task_tag_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskTagId;
	
	// タスクタグ名
	@Column(name = "task_tag_name")
	private String TaskTagName;
	
	// リレーション
	@OneToMany(mappedBy = "taskTag")
	private List<Task> tasks;
}
