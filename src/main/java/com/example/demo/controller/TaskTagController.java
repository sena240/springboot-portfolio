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

import com.example.demo.entity.TaskTag;
import com.example.demo.service.TaskTagService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TaskTagController {

	private final TaskTagService taskTagService;

	@GetMapping("/taskTag/list")
	public String searchTaskTag(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 12);
		Page<TaskTag> taskTags;
		taskTags = taskTagService.searchAll(pageable);

		model.addAttribute("taskTags", taskTags.getContent());
		model.addAttribute("page", taskTags);
		return "taskTag/list";
	}

	@GetMapping("/taskTag/add")
	public String addTaskTag(@ModelAttribute TaskTag taskTag) {
		return "taskTag/addForm";
	}

	@PostMapping("/taskTag/processAdd")
	public String processAdd(@Validated @ModelAttribute TaskTag taskTag, BindingResult result) {
		if (result.hasErrors()) {
			return "/taskTag/addForm";
		}
		taskTagService.saveTaskTag(taskTag);
		return "redirect:/taskTag/list?addToast=true";
	}

	@PostMapping("/taskTag/processEdit")
	public String processEdit(@Validated @ModelAttribute TaskTag taskTag, BindingResult result) {
		if (result.hasErrors()) {
			return "/taskTag/editForm";
		}
		taskTagService.saveTaskTag(taskTag);
		return "redirect:/taskTag/list?editToast=true";
	}

	@GetMapping("/taskTag/edit/{taskTagId}")
	public String editTaskTag(@PathVariable Long taskTagId, Model model) {
		Optional<TaskTag> optionalTaskTag = taskTagService.editTaskTag(taskTagId);

		if (optionalTaskTag.isPresent()) {
			TaskTag taskTag = optionalTaskTag.get();
			model.addAttribute("taskTag", taskTag);
			model.addAttribute("taskTagTags", taskTagService.allTaskTag());
		}
		return "taskTag/editForm";
	}

	@GetMapping("/taskTag/delete/{taskTagId}")
	public String deleteTaskTag(@PathVariable Long taskTagId) {
		if (taskTagService.deleteTaskTag(taskTagId)) {
			return "redirect:/taskTag/list?deleteToast=true";
		} else {
			return "redirect:/taskTag/list?deleteToast=false";
		}
	}
}
