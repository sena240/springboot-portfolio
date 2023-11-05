package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProjectTag;
import com.example.demo.repository.ProjectTagRepository;

@Service
public class ProjectTagService {

	private final ProjectTagRepository projectTagRepository;

	public ProjectTagService(ProjectTagRepository projectTagRepository) {
			this.projectTagRepository = projectTagRepository;
		}

	public Page<ProjectTag> searchAll(Pageable pageable) {
		return projectTagRepository.findAll(pageable);
	}

	public Optional<ProjectTag> editProjectTag(Long projectTagId) {
		return projectTagRepository.findById(projectTagId);
	}

	public boolean deleteProjectTag(Long projectTagId) {
		try {
			projectTagRepository.deleteById(projectTagId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public ProjectTag saveProjectTag(ProjectTag projectTag) {
		return projectTagRepository.save(projectTag);
	}

	public List<ProjectTag> allProjectTag() {
		return projectTagRepository.findAll();
	}
}
