package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MeetingRoom;
import com.example.demo.repository.MeetingRoomRepository;

@Service
public class MeetingRoomService {
	private final MeetingRoomRepository meetingRoomRepository;

	public MeetingRoomService(MeetingRoomRepository meetingRoomRepository) {
		this.meetingRoomRepository = meetingRoomRepository;
	}
	
	public Page<MeetingRoom> searchAll(Pageable pageable) {
		return meetingRoomRepository.findAll(pageable);
	}

	public Optional<MeetingRoom> editMeetingRoom(Long meetingRoomId) {
		return meetingRoomRepository.findById(meetingRoomId);
	}

	public boolean deleteMeetingRoom(Long meetingRoomId) {
		try {
			meetingRoomRepository.deleteById(meetingRoomId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public MeetingRoom saveMeetingRoom(MeetingRoom meetingRoom) {
		return meetingRoomRepository.save(meetingRoom);
	}
	
	public List<MeetingRoom> allMeetingRoom() {
		return meetingRoomRepository.findAll();
	}
}
