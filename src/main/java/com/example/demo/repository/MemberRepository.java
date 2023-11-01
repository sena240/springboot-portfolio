package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	public Page<Member> findAll(Pageable pageable);

	@Query("SELECT m FROM Member m WHERE m.memberName LIKE %:query% OR m.mail LIKE %:query% OR m.phone LIKE %:query%")
	Page<Member> searchByQuery(@Param("query") String query, Pageable pageable);

}
