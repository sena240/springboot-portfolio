package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Meeting;
import com.example.demo.repository.MeetingRepository;

@Service
public class MeetingService {

	private final MeetingRepository meetingRepository;

	public MeetingService(MeetingRepository meetingRepository) {
		this.meetingRepository = meetingRepository;
	}

	public Page<Meeting> searchAll(Pageable pageable) {
		return meetingRepository.findAll(pageable);
	}

	public Page<Meeting> searchMeeting(String query, Pageable pageable) {
		String searchQuery = "%" + query + "%";
		return meetingRepository.searchByQuery(searchQuery, pageable);
	}

	public Optional<Meeting> editMeeting(Long meetingId) {
		return meetingRepository.findById(meetingId);
	}

	public boolean deleteMeeting(Long meetingId) {
		try {
			meetingRepository.deleteById(meetingId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Meeting saveMeeting(Meeting meeting) {
		return meetingRepository.save(meeting);
	}
	
	public List<Meeting> allMeeting() {
		return meetingRepository.findAll();
	}
}
