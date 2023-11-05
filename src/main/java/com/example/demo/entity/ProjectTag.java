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
@Table(name = "project_tag")
public class ProjectTag {
	
	// ID
	@Id
	@Column(name = "project_tag_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectTagId;
	
	// プロジェクトタグ名
	@NotBlank(message = "プロジェクトタグ名を入力してください")
	@Column(name = "project_tag_name")
	private String projectTagName;

	// リレーション
	@OneToMany(mappedBy = "projectTag")
	private List<Project> projects;
}
