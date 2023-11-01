package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "project")
public class Project {
	
	// ID
	@Id
	@Column(name = "project_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectId;
	
	// プロジェクト名
	@Column(name = "project_name")
	private String projectName;
	
	// プロジェクトタグID（FK）
	@ManyToOne
	private ProjectTag projectTag;
	
	// 概要
	@Column(name = "project_summary")
	private String projectSummary;
	
	// 開始日
	@Column(name = "start_date")
	private LocalDate startDate;
	
	// 終了日
	@Column(name = "end_date")
	private LocalDate endDate;
	
	// リレーション
	@OneToMany(mappedBy = "project")
	private List<Meeting> meetings;
	
	@OneToMany(mappedBy = "project")
	private List<Task> tasks;
	
	@ManyToMany
    @JoinTable(name = "project_member",
    joinColumns = @JoinColumn(name = "project_id"),
    inverseJoinColumns = @JoinColumn(name = "member_id")
    )
	
	private List<Member> members;
}
