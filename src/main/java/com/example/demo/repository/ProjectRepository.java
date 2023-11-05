package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	public Page<Project> findAll(Pageable pageable);

	@Query("SELECT p FROM Project p WHERE p.projectName LIKE %:query% OR p.projectTag.projectTagName LIKE %:query%")
	Page<Project> searchByQuery(@Param("query") String query, Pageable pageable);
}
