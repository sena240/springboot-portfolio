package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Meeting;
import com.example.demo.entity.MeetingRoom;
import com.example.demo.entity.MeetingTag;
import com.example.demo.entity.Project;
import com.example.demo.service.MeetingRoomService;
import com.example.demo.service.MeetingService;
import com.example.demo.service.MeetingTagService;
import com.example.demo.service.ProjectService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MeetingController {
	
	private final MeetingService meetingService;
	private final MeetingTagService meetingTagService;
	private final MeetingRoomService meetingRoomService;
	private final ProjectService projectService;

	@ModelAttribute("meetingTags")
	public List<MeetingTag> populateMeetingTags() {
		return meetingTagService.allMeetingTag();
	}
	@ModelAttribute("meetingRooms")
	public List<MeetingRoom> populateMeetingRooms() {
		return meetingRoomService.allMeetingRoom();
	}
	@ModelAttribute("projects")
	public List<Project> populateProjects() {
		return projectService.allProject();
	}

	@GetMapping("/meeting/list")
	public String searchMeeting(@RequestParam(name = "query", required = false, defaultValue = "") String query,
			@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 12);
		Page<Meeting> meetings;
		if (query != null && !query.isEmpty()) {
			meetings = meetingService.searchMeeting(query, pageable);
		} else {
			meetings = meetingService.searchAll(pageable);
		}
		model.addAttribute("meetings", meetings.getContent());
		model.addAttribute("page", meetings);
		model.addAttribute("query", query);
		return "meeting/list";
	}

	@GetMapping("/meeting/add")
	public String addMeeting(@ModelAttribute Meeting meeting) {
		return "meeting/addForm";
	}

	@PostMapping("/meeting/processAdd")
	public String processAdd(@Validated @ModelAttribute Meeting meeting, BindingResult result) {
		if (result.hasErrors()) {
			return "/meeting/addForm";
		}
		meetingService.saveMeeting(meeting);
		return "redirect:/meeting/list?addToast=true";
	}

	@PostMapping("/meeting/processEdit")
	public String processEdit(@Validated @ModelAttribute Meeting meeting, BindingResult result) {
		if (result.hasErrors()) {
			return "/meeting/editForm";
		}
		meetingService.saveMeeting(meeting);
		return "redirect:/meeting/list?editToast=true";
	}

	@GetMapping("/meeting/edit/{meetingId}")
	public String editMeeting(@PathVariable Long meetingId, Model model) {
		Optional<Meeting> optionalMeeting = meetingService.editMeeting(meetingId);

		if (optionalMeeting.isPresent()) {
			Meeting meeting = optionalMeeting.get();
			model.addAttribute("meeting", meeting);
			model.addAttribute("meetingTags", meetingTagService.allMeetingTag());
			model.addAttribute("meetingRooms", meetingRoomService.allMeetingRoom());
			model.addAttribute("projects", projectService.allProject());
		}
		return "meeting/editForm";
	}

	@GetMapping("/meeting/delete/{meetingId}")
	public String deleteMeeting(@PathVariable Long meetingId) {
		if (meetingService.deleteMeeting(meetingId)) {
			return "redirect:/meeting/list?deleteToast=true";
		} else {
			return "redirect:/meeting/list?deleteToast=false";
		}
	}
}
