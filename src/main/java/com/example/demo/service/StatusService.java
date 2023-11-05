package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Status;
import com.example.demo.repository.StatusRepository;

@Service
public class StatusService {
	
	private final StatusRepository statusRepository;

	public StatusService(StatusRepository statusRepository) {
		this.statusRepository = statusRepository;
	}
	
	public Page<Status> searchAll(Pageable pageable) {
		return statusRepository.findAll(pageable);
	}

	public Optional<Status> editStatus(Long statusId) {
		return statusRepository.findById(statusId);
	}

	public boolean deleteStatus(Long statusId) {
		try {
			statusRepository.deleteById(statusId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Status saveStatus(Status status) {
		return statusRepository.save(status);
	}
	
	public List<Status> allStatus() {
		return statusRepository.findAll();
	}
}
