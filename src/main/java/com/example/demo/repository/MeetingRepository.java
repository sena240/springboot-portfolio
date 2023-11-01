package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

}
