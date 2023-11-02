package com.example.demo.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Member;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.MemberService;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報 Controller
 */

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final DepartmentService departmentService;

	@GetMapping("/member/list")
	public String showList(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 12);
		Page<Member> memberPage = memberService.getPage(pageable);
		model.addAttribute("members", memberPage.getContent());
		model.addAttribute("page", memberPage);
		return "member/list";
	}

	@GetMapping("/member/search")
	public String searchMember(@RequestParam(name = "query", required = false) String query,
			@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 3);
		Page<Member> members;
		if (query != null && !query.isEmpty()) {
			members = memberService.searchMember(query, pageable);
		} else {
			members = memberService.searchAll(pageable);
		}
		model.addAttribute("members", members.getContent());
		model.addAttribute("page", members);
		return "member/list";
	}

	@GetMapping("/add")
	public String addEmployee(@ModelAttribute Member member) {
		return "member/addForm";
	}

	@PostMapping("/process")
	public String process(@ModelAttribute Member member) {
		memberService.saveMember(member);
		return "redirect:/member/list?addToast=true";
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
