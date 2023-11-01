package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MeetingTag;

public interface MeetingTagRepository extends JpaRepository<MeetingTag, Long> {

}
