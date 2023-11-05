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

import com.example.demo.entity.Status;
import com.example.demo.service.StatusService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StatusController {
	
	private final StatusService statusService;

	@GetMapping("/status/list")
	public String searchStatus(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 12);
		Page<Status> statuss;
		statuss = statusService.searchAll(pageable);

		model.addAttribute("statuss", statuss.getContent());
		model.addAttribute("page", statuss);
		return "status/list";
	}

	@GetMapping("/status/add")
	public String addStatus(@ModelAttribute Status status) {
		return "status/addForm";
	}

	@PostMapping("/status/processAdd")
	public String processAdd(@Validated @ModelAttribute Status status, BindingResult result) {
		if (result.hasErrors()) {
			return "/status/addForm";
		}
		statusService.saveStatus(status);
		return "redirect:/status/list?addToast=true";
	}

	@PostMapping("/status/processEdit")
	public String processEdit(@Validated @ModelAttribute Status status, BindingResult result) {
		if (result.hasErrors()) {
			return "/status/editForm";
		}
		statusService.saveStatus(status);
		return "redirect:/status/list?editToast=true";
	}

	@GetMapping("/status/edit/{statusId}")
	public String editStatus(@PathVariable Long statusId, Model model) {
		Optional<Status> optionalStatus = statusService.editStatus(statusId);

		if (optionalStatus.isPresent()) {
			Status status = optionalStatus.get();
			model.addAttribute("status", status);
			model.addAttribute("statusTags", statusService.allStatus());
		}
		return "status/editForm";
	}

	@GetMapping("/status/delete/{statusId}")
	public String deleteStatus(@PathVariable Long statusId) {
		if (statusService.deleteStatus(statusId)) {
			return "redirect:/status/list?deleteToast=true";
		} else {
			return "redirect:/status/list?deleteToast=false";
		}
	}
}
