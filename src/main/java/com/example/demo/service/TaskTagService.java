package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TaskTag;
import com.example.demo.repository.TaskTagRepository;

@Service
public class TaskTagService {
	
	private final TaskTagRepository taskTagRepository;

	public TaskTagService(TaskTagRepository taskTagRepository) {
		this.taskTagRepository = taskTagRepository;
	}
	
	public Page<TaskTag> searchAll(Pageable pageable) {
		return taskTagRepository.findAll(pageable);
	}

	public Optional<TaskTag> editTaskTag(Long taskTagId) {
		return taskTagRepository.findById(taskTagId);
	}

	public boolean deleteTaskTag(Long taskTagId) {
		try {
			taskTagRepository.deleteById(taskTagId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public TaskTag saveTaskTag(TaskTag taskTag) {
		return taskTagRepository.save(taskTag);
	}
	
	public List<TaskTag> allTaskTag() {
		return taskTagRepository.findAll();
	}
}
