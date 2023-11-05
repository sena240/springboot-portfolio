package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	public Page<Task> findAll(Pageable pageable);

	@Query("SELECT t FROM Task t WHERE t.taskName LIKE %:query% OR t.taskTag.taskTagName LIKE %:query%")
	Page<Task> searchByQuery(@Param("query") String query, Pageable pageable);
}
