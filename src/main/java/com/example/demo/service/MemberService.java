package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Page<Member> searchAll(Pageable pageable) {
		return memberRepository.findAll(pageable);
	}

	public Page<Member> searchMember(String query, Pageable pageable) {
		String searchQuery = "%" + query + "%";
		return memberRepository.searchByQuery(searchQuery, pageable);
	}

	public Optional<Member> editMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

	public boolean deleteMember(Long memberId) {
		try {
			memberRepository.deleteById(memberId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Member saveMember(Member member) {
		return memberRepository.save(member);
	}
	
	public List<Member> allMember() {
		return memberRepository.findAll();
	}
}
