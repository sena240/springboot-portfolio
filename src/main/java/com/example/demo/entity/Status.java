package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "status")
public class Status {
	
	// ID
	@Id
	@Column(name = "status_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long statusId;
	
	// ステータス名
	@NotBlank(message = "ステータス名を入力してください")
	@Column(name = "status_name")
	private String statusName;
	
	// リレーション
	@OneToMany(mappedBy = "status")
	private List<Task> tasks;
}
