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

import com.example.demo.entity.ProjectTag;
import com.example.demo.service.ProjectTagService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectTagController {

	private final ProjectTagService projectTagService;

	@GetMapping("/projectTag/list")
	public String searchProjectTag(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 12);
		Page<ProjectTag> projectTags;
		projectTags = projectTagService.searchAll(pageable);

		model.addAttribute("projectTags", projectTags.getContent());
		model.addAttribute("page", projectTags);
		return "projectTag/list";
	}

	@GetMapping("/projectTag/add")
	public String addProjectTag(@ModelAttribute ProjectTag projectTag) {
		return "projectTag/addForm";
	}

	@PostMapping("/projectTag/processAdd")
	public String processAdd(@Validated @ModelAttribute ProjectTag projectTag, BindingResult result) {
		if (result.hasErrors()) {
			return "/projectTag/addForm";
		}
		projectTagService.saveProjectTag(projectTag);
		return "redirect:/projectTag/list?addToast=true";
	}

	@PostMapping("/projectTag/processEdit")
	public String processEdit(@Validated @ModelAttribute ProjectTag projectTag, BindingResult result) {
		if (result.hasErrors()) {
			return "/projectTag/editForm";
		}
		projectTagService.saveProjectTag(projectTag);
		return "redirect:/projectTag/list?editToast=true";
	}

	@GetMapping("/projectTag/edit/{projectTagId}")
	public String editProjectTag(@PathVariable Long projectTagId, Model model) {
		Optional<ProjectTag> optionalProjectTag = projectTagService.editProjectTag(projectTagId);

		if (optionalProjectTag.isPresent()) {
			ProjectTag projectTag = optionalProjectTag.get();
			model.addAttribute("projectTag", projectTag);
			model.addAttribute("projectTagTags", projectTagService.allProjectTag());
		}
		return "projectTag/editForm";
	}

	@GetMapping("/projectTag/delete/{projectTagId}")
	public String deleteProjectTag(@PathVariable Long projectTagId) {
		if (projectTagService.deleteProjectTag(projectTagId)) {
			return "redirect:/projectTag/list?deleteToast=true";
		} else {
			return "redirect:/projectTag/list?deleteToast=false";
		}
	}
}
