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
@Table(name = "meeting_room")
public class MeetingRoom {
	
	// ID
	@Id
	@Column(name = "meeting_room_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meetingRoomId;
	
	// 会議室名
	@Column(name = "meeting_room_name")
	private String meetingRoomName;
	
	// 会議室の場所
	@Column(name = "meeting_room_place")
	private String meetingRoomPlace;
	
	// リレーション
	@OneToMany(mappedBy = "meetingRoom")
	private List<Meeting> meetings;
}
