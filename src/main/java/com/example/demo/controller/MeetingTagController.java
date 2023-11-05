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

import com.example.demo.entity.MeetingTag;
import com.example.demo.service.MeetingTagService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MeetingTagController {
	
	private final MeetingTagService meetingTagService;

	@GetMapping("/meetingTag/list")
	public String searchMeetingTag(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 12);
		Page<MeetingTag> meetingTags;
		meetingTags = meetingTagService.searchAll(pageable);

		model.addAttribute("meetingTags", meetingTags.getContent());
		model.addAttribute("page", meetingTags);
		return "meetingTag/list";
	}

	@GetMapping("/meetingTag/add")
	public String addMeetingTag(@ModelAttribute MeetingTag meetingTag) {
		return "meetingTag/addForm";
	}

	@PostMapping("/meetingTag/processAdd")
	public String processAdd(@Validated @ModelAttribute MeetingTag meetingTag, BindingResult result) {
		if (result.hasErrors()) {
			return "/meetingTag/addForm";
		}
		meetingTagService.saveMeetingTag(meetingTag);
		return "redirect:/meetingTag/list?addToast=true";
	}

	@PostMapping("/meetingTag/processEdit")
	public String processEdit(@Validated @ModelAttribute MeetingTag meetingTag, BindingResult result) {
		if (result.hasErrors()) {
			return "/meetingTag/editForm";
		}
		meetingTagService.saveMeetingTag(meetingTag);
		return "redirect:/meetingTag/list?editToast=true";
	}

	@GetMapping("/meetingTag/edit/{meetingTagId}")
	public String editMeetingTag(@PathVariable Long meetingTagId, Model model) {
		Optional<MeetingTag> optionalMeetingTag = meetingTagService.editMeetingTag(meetingTagId);

		if (optionalMeetingTag.isPresent()) {
			MeetingTag meetingTag = optionalMeetingTag.get();
			model.addAttribute("meetingTag", meetingTag);
			model.addAttribute("meetingTagTags", meetingTagService.allMeetingTag());
		}
		return "meetingTag/editForm";
	}

	@GetMapping("/meetingTag/delete/{meetingTagId}")
	public String deleteMeetingTag(@PathVariable Long meetingTagId) {
		if (meetingTagService.deleteMeetingTag(meetingTagId)) {
			return "redirect:/meetingTag/list?deleteToast=true";
		} else {
			return "redirect:/meetingTag/list?deleteToast=false";
		}
	}
}
