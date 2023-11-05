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
import com.example.demo.entity.Member;
import com.example.demo.entity.Project;
import com.example.demo.entity.Status;
import com.example.demo.entity.Task;
import com.example.demo.entity.TaskTag;
import com.example.demo.service.MeetingService;
import com.example.demo.service.MemberService;
import com.example.demo.service.ProjectService;
import com.example.demo.service.StatusService;
import com.example.demo.service.TaskService;
import com.example.demo.service.TaskTagService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TaskController {
	private final TaskService taskService;
	private final TaskTagService taskTagService;
	private final StatusService statusService;
	private final ProjectService projectService;
	private final MeetingService meetingService;
	private final MemberService memberService;

	@ModelAttribute("taskTags")
	public List<TaskTag> populateTaskTags() {
		return taskTagService.allTaskTag();
	}
	@ModelAttribute("statuses")
	public List<Status> populateStatuses() {
		return statusService.allStatus();
	}
	@ModelAttribute("projects")
	public List<Project> populateProjects() {
		return projectService.allProject();
	}
	@ModelAttribute("meetings")
	public List<Meeting> populateMeetings() {
		return meetingService.allMeeting();
	}
	@ModelAttribute("members")
	public List<Member> populateMembers() {
		return memberService.allMember();
	}

	@GetMapping("/task/list")
	public String searchTask(@RequestParam(name = "query", required = false, defaultValue = "") String query,
			@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 12);
		Page<Task> tasks;
		if (query != null && !query.isEmpty()) {
			tasks = taskService.searchTask(query, pageable);
		} else {
			tasks = taskService.searchAll(pageable);
		}
		model.addAttribute("tasks", tasks.getContent());
		model.addAttribute("page", tasks);
		model.addAttribute("query", query);
		return "task/list";
	}

	@GetMapping("/task/add")
	public String addTask(@ModelAttribute Task task) {
		return "task/addForm";
	}

	@PostMapping("/task/processAdd")
	public String processAdd(@Validated @ModelAttribute Task task, BindingResult result) {
		if (result.hasErrors()) {
			return "/task/addForm";
		}
		taskService.saveTask(task);
		return "redirect:/task/list?addToast=true";
	}

	@PostMapping("/task/processEdit")
	public String processEdit(@Validated @ModelAttribute Task task, BindingResult result) {
		if (result.hasErrors()) {
			return "/task/editForm";
		}
		taskService.saveTask(task);
		return "redirect:/task/list?editToast=true";
	}

	@GetMapping("/task/edit/{taskId}")
	public String editTask(@PathVariable Long taskId, Model model) {
		Optional<Task> optionalTask = taskService.editTask(taskId);

		if (optionalTask.isPresent()) {
			Task task = optionalTask.get();
			model.addAttribute("task", task);
			model.addAttribute("taskTags", taskTagService.allTaskTag());
			model.addAttribute("statuses", statusService.allStatus());
			model.addAttribute("projects", projectService.allProject());
			model.addAttribute("meetings", meetingService.allMeeting());
			model.addAttribute("members", memberService.allMember());
		}
		return "task/editForm";
	}

	@GetMapping("/task/delete/{taskId}")
	public String deleteTask(@PathVariable Long taskId) {
		if (taskService.deleteTask(taskId)) {
			return "redirect:/task/list?deleteToast=true";
		} else {
			return "redirect:/task/list?deleteToast=false";
		}		
	}
}
