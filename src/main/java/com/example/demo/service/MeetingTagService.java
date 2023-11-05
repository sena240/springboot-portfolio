package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MeetingTag;
import com.example.demo.repository.MeetingTagRepository;

@Service
public class MeetingTagService {
	private final MeetingTagRepository meetingTagRepository;

	public MeetingTagService(MeetingTagRepository meetingTagRepository) {
		this.meetingTagRepository = meetingTagRepository;
	}

	public Page<MeetingTag> searchAll(Pageable pageable) {
		return meetingTagRepository.findAll(pageable);
	}

	public Optional<MeetingTag> editMeetingTag(Long meetingTagId) {
		return meetingTagRepository.findById(meetingTagId);
	}

	public boolean deleteMeetingTag(Long meetingTagId) {
		try {
			meetingTagRepository.deleteById(meetingTagId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public MeetingTag saveMeetingTag(MeetingTag meetingTag) {
		return meetingTagRepository.save(meetingTag);
	}
	
	public List<MeetingTag> allMeetingTag() {
		return meetingTagRepository.findAll();
	}
}
