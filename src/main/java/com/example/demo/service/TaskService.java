package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public Page<Task> searchAll(Pageable pageable) {
		return taskRepository.findAll(pageable);
	}

	public Page<Task> searchTask(String query, Pageable pageable) {
		String searchQuery = "%" + query + "%";
		return taskRepository.searchByQuery(searchQuery, pageable);
	}

	public Optional<Task> editTask(Long taskId) {
		return taskRepository.findById(taskId);
	}

	public boolean deleteTask(Long taskId) {
		try {
			taskRepository.deleteById(taskId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Task saveTask(Task task) {
		return taskRepository.save(task);
	}

	public List<Task> allTask() {
		return taskRepository.findAll();
	}
}
