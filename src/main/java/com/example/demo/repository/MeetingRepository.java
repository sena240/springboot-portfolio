package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
	
	public Page<Meeting> findAll(Pageable pageable);
	
	@Query("SELECT m FROM Meeting m WHERE m.meetingName LIKE %:query% OR m.meetingTag.meetingTagName LIKE %:query% OR m.project.projectName LIKE %:query% OR m.meetingRoom.meetingRoomName LIKE %:query%")
	Page<Meeting> searchByQuery(@Param("query") String query, Pageable pageable);

}
