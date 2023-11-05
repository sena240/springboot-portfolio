package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Project;
import com.example.demo.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {

	private final ProjectRepository projectRepository;

	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public Page<Project> searchAll(Pageable pageable) {
		return projectRepository.findAll(pageable);
	}

	public Page<Project> searchProject(String query, Pageable pageable) {
		String searchQuery = "%" + query + "%";
		return projectRepository.searchByQuery(searchQuery, pageable);
	}

	@Transactional
	public Optional<Project> editProject(Long projectId) {
		Optional<Project> project = projectRepository.findById(projectId);
		project.ifPresent(p -> {
			Hibernate.initialize(p.getMembers());
		});
		return project;
	}

	public boolean deleteProject(Long projectId) {
		try {
			projectRepository.deleteById(projectId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Project saveProject(Project project) {
		return projectRepository.save(project);
	}

	public List<Project> allProject() {
		return projectRepository.findAll();
	}
}
