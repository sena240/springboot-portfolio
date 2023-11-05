package com.example.demo.controller;

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

import com.example.demo.entity.MeetingRoom;
import com.example.demo.service.MeetingRoomService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MeetingRoomController {

	private final MeetingRoomService meetingRoomService;

	@GetMapping("/meetingRoom/list")
	public String searchMeetingRoom(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 12);
		Page<MeetingRoom> meetingRooms;
		meetingRooms = meetingRoomService.searchAll(pageable);

		model.addAttribute("meetingRooms", meetingRooms.getContent());
		model.addAttribute("page", meetingRooms);
		return "meetingRoom/list";
	}

	@GetMapping("/meetingRoom/add")
	public String addMeetingRoom(@ModelAttribute MeetingRoom meetingRoom) {
		return "meetingRoom/addForm";
	}

	@PostMapping("/meetingRoom/processAdd")
	public String processAdd(@Validated @ModelAttribute MeetingRoom meetingRoom, BindingResult result) {
		if (result.hasErrors()) {
			return "/meetingRoom/addForm";
		}
		meetingRoomService.saveMeetingRoom(meetingRoom);
		return "redirect:/meetingRoom/list?addToast=true";
	}

	@PostMapping("/meetingRoom/processEdit")
	public String processEdit(@Validated @ModelAttribute MeetingRoom meetingRoom, BindingResult result) {
		if (result.hasErrors()) {
			return "/meetingRoom/editForm";
		}
		meetingRoomService.saveMeetingRoom(meetingRoom);
		return "redirect:/meetingRoom/list?editToast=true";
	}

	@GetMapping("/meetingRoom/edit/{meetingRoomId}")
	public String editMeetingRoom(@PathVariable Long meetingRoomId, Model model) {
		Optional<MeetingRoom> optionalMeetingRoom = meetingRoomService.editMeetingRoom(meetingRoomId);

		if (optionalMeetingRoom.isPresent()) {
			MeetingRoom meetingRoom = optionalMeetingRoom.get();
			model.addAttribute("meetingRoom", meetingRoom);
			model.addAttribute("meetingRoomTags", meetingRoomService.allMeetingRoom());
		}
		return "meetingRoom/editForm";
	}

	@GetMapping("/meetingRoom/delete/{meetingRoomId}")
	public String deleteMeetingRoom(@PathVariable Long meetingRoomId) {
		if (meetingRoomService.deleteMeetingRoom(meetingRoomId)) {
			return "redirect:/meetingRoom/list?deleteToast=true";
		} else {
			return "redirect:/meetingRoom/list?deleteToast=false";
		}
	}

}
