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

import com.example.demo.entity.Project;
import com.example.demo.entity.ProjectTag;
import com.example.demo.service.MemberService;
import com.example.demo.service.ProjectService;
import com.example.demo.service.ProjectTagService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectController {

	private final ProjectService projectService;
	private final ProjectTagService projectTagService;
	private final MemberService memberService;

	@ModelAttribute("projectTags")
	public List<ProjectTag> populateProjectTags() {
		return projectTagService.allProjectTag();
	}

	@GetMapping("/project/list")
	public String searchProject(@RequestParam(name = "query", required = false, defaultValue = "") String query,
			@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 12);
		Page<Project> projects;
		if (query != null && !query.isEmpty()) {
			projects = projectService.searchProject(query, pageable);
		} else {
			projects = projectService.searchAll(pageable);
		}
		model.addAttribute("projects", projects.getContent());
		model.addAttribute("page", projects);
		model.addAttribute("query", query);
		return "project/list";
	}

	@GetMapping("/project/add")
	public String addProjectForm(Model model) {
		model.addAttribute("project", new Project());
		model.addAttribute("members", memberService.allMember());
		return "project/addForm";
	}

	@PostMapping("/project/processAdd")
	public String processAdd(@Validated @ModelAttribute Project project, BindingResult result, Model model) {
		boolean dateError = false;

		if (project.getStartDate() != null && project.getEndDate() != null) {
			if (project.getEndDate().isBefore(project.getStartDate())) {
				result.rejectValue("endDate", "error.project", "終了日は開始日よりも後の日付でなければなりません。");
				dateError = true;
			}
		}
		if (result.hasErrors() || dateError) {
			model.addAttribute("members", memberService.allMember());
			project.setMembers(null);
			return "/project/addForm";
		}
		projectService.saveProject(project);
		return "redirect:/project/list?addToast=true";
	}

	@PostMapping("/project/processEdit")
	public String processEdit(@Validated @ModelAttribute Project project, BindingResult result, Model model) {
		boolean dateError = false;

		if (project.getStartDate() != null && project.getEndDate() != null) {
			if (project.getEndDate().isBefore(project.getStartDate())) {
				result.rejectValue("endDate", "error.project", "終了日は開始日よりも後の日付でなければなりません。");
				dateError = true;
			}
		}
		if (result.hasErrors() || dateError) {
			project.setMembers(null);
			model.addAttribute("members", memberService.allMember());
			return "/project/editForm";
		}
		projectService.saveProject(project);
		return "redirect:/project/list?editToast=true";
	}

	@GetMapping("/project/edit/{projectId}")
	public String editProject(@PathVariable Long projectId, Model model) {
		Optional<Project> optionalProject = projectService.editProject(projectId);
	
		if (optionalProject.isPresent()) {
			Project project = optionalProject.get();
			project.setMembers(null);
			model.addAttribute("project", project);
			model.addAttribute("members", memberService.allMember());
		}
		return "project/editForm";
	}

	@GetMapping("/project/delete/{projectId}")
	public String deleteProject(@PathVariable Long projectId) {
		if (projectService.deleteProject(projectId)) {
			return "redirect:/project/list?deleteToast=true";
		} else {
			return "redirect:/project/list?deleteToast=false";
		}
	}
}
