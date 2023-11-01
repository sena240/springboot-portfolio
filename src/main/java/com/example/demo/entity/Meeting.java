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
@Table(name = "meeting")
public class Meeting {
	
	// ID
	@Id
	@Column(name = "meeting_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meetingId;
	
	// 議事録名
	@Column(name = "meeting_name")
	private String meetingName;
	
	// 議事録タグID（FK1）
	@ManyToOne
	private MeetingTag meetingTag;
	
	// 概要
	@Column(name = "meeting_summary")
	private String meetingSummary;
	
	// 会議日時
	@Column(name = "meeting_date")
	private LocalDate meetingDate;
	
	// プロジェクトID（FK2）
	@ManyToOne
	private Project project;
	
	// 会議室ID（FK3）
	@ManyToOne
	private MeetingRoom meetingRoom;
	
	// リレーション
	@OneToMany(mappedBy = "meeting")
	private List<MeetingDocument> meetingDocuments;
	
	@ManyToMany
    @JoinTable(name = "meeting_member",
    joinColumns = @JoinColumn(name = "meeting_id"),
    inverseJoinColumns = @JoinColumn(name = "member_id")
    )
	
	private List<Member> members;
}
