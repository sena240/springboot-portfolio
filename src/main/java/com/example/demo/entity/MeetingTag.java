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
@Table(name = "meeting_tag")
public class MeetingTag {
	
	// ID
	@Id
	@Column(name = "meeting_tag_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meetingTagId;
	
	// 議事録タグ名
	@NotBlank(message = "議事録タグ名を入力してください")
	@Column(name = "meeting_tag_name")
	private String meetingTagName;
	
	// リレーション
	@OneToMany(mappedBy = "meetingTag")
	private List<Meeting> meetings;
}
