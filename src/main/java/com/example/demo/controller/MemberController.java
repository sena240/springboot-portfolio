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

import com.example.demo.entity.Department;
import com.example.demo.entity.Member;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final DepartmentService departmentService;

	@ModelAttribute("departments")
	public List<Department> populateDepartments() {
		return departmentService.allDepartment();
	}

	@GetMapping("/member/list")
	public String searchMember(@RequestParam(name = "query", required = false, defaultValue = "") String query,
			@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 12);
		Page<Member> members;
		if (query != null && !query.isEmpty()) {
			members = memberService.searchMember(query, pageable);
		} else {
			members = memberService.searchAll(pageable);
		}
		model.addAttribute("members", members.getContent());
		model.addAttribute("page", members);
		model.addAttribute("query", query);
		return "member/list";
	}

	@GetMapping("/add")
	public String addEmployee(@ModelAttribute Member member) {
		return "member/addForm";
	}

	@PostMapping("/processAdd")
	public String processAdd(@Validated @ModelAttribute Member member, BindingResult result) {
		if (result.hasErrors()) {
			return "/member/addForm";
		}
		memberService.saveMember(member);
		return "redirect:/member/list?addToast=true";
	}

	@PostMapping("/processEdit")
	public String processEdit(@Validated @ModelAttribute Member member, BindingResult result) {
		if (result.hasErrors()) {
			return "/member/editForm";
		}
		memberService.saveMember(member);
		return "redirect:/member/list?editToast=true";
	}

	@GetMapping("/edit/{memberId}")
	public String editMember(@PathVariable Long memberId, Model model) {
		Optional<Member> optionalMember = memberService.editMember(memberId);

		if (optionalMember.isPresent()) {
			Member member = optionalMember.get();
			model.addAttribute("member", member);
			model.addAttribute("departments", departmentService.allDepartment());

			if (member.getDepartment() != null) {
				model.addAttribute("selectedDepartmentId", member.getDepartment().getDepartmentId());
			}
		}
		return "member/editForm";
	}

	@GetMapping("/delete/{memberId}")
	public String deleteMember(@PathVariable Long memberId) {
		memberService.deleteMember(memberId);
		return "redirect:/member/list?deleteToast=true";
	}

}
