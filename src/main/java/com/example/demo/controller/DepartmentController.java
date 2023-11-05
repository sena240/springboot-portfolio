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

import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentService departmentService;

	@GetMapping("/department/list")
	public String searchDepartment(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 12);
		Page<Department> departments;
		departments = departmentService.searchAll(pageable);

		model.addAttribute("departments", departments.getContent());
		model.addAttribute("page", departments);
		return "department/list";
	}

	@GetMapping("/department/add")
	public String addDepartment(@ModelAttribute Department department) {
		return "department/addForm";
	}

	@PostMapping("/department/processAdd")
	public String processAdd(@Validated @ModelAttribute Department department, BindingResult result) {
		if (result.hasErrors()) {
			return "/department/addForm";
		}
		departmentService.saveDepartment(department);
		return "redirect:/department/list?addToast=true";
	}

	@PostMapping("/department/processEdit")
	public String processEdit(@Validated @ModelAttribute Department department, BindingResult result) {
		if (result.hasErrors()) {
			return "/department/editForm";
		}
		departmentService.saveDepartment(department);
		return "redirect:/department/list?editToast=true";
	}

	@GetMapping("/department/edit/{departmentId}")
	public String editDepartment(@PathVariable Long departmentId, Model model) {
		Optional<Department> optionalDepartment = departmentService.editDepartment(departmentId);

		if (optionalDepartment.isPresent()) {
			Department department = optionalDepartment.get();
			model.addAttribute("department", department);
			model.addAttribute("departmentTags", departmentService.allDepartment());
		}
		return "department/editForm";
	}

	@GetMapping("/department/delete/{departmentId}")
	public String deleteDepartment(@PathVariable Long departmentId) {
		if (departmentService.deleteDepartment(departmentId)) {
			return "redirect:/department/list?deleteToast=true";
		} else {
			return "redirect:/department/list?deleteToast=false";
		}
	}

}
