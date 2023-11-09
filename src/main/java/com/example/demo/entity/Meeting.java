package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotBlank(message = "議事録名を入力してください")
	@Column(name = "meeting_name")
	private String meetingName;
	
	// 議事録タグID（FK1）
	@ManyToOne
	private MeetingTag meetingTag;
	
	// 概要
	@Column(name = "meeting_summary")
	private String meetingSummary;
	
	// 会議日
	@NotNull(message = "会議日を入力してください")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "meeting_date")
	private LocalDate meetingDate;
	
	// プロジェクトID（FK2）
	@ManyToOne
	private Project project;
	
	// 会議室ID（FK3）
	@ManyToOne
	private MeetingRoom meetingRoom;
	
	// リレーション
	@NotNull(message = "会議参加メンバーを選択してください")
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "meeting_member",
    joinColumns = @JoinColumn(name = "meeting_id"),
    inverseJoinColumns = @JoinColumn(name = "member_id")
    )
	
	private List<Member> members;
}
