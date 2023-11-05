package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {
	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	
	public Page<Department> searchAll(Pageable pageable) {
		return departmentRepository.findAll(pageable);
	}

	public Optional<Department> editDepartment(Long departmentId) {
		return departmentRepository.findById(departmentId);
	}

	public boolean deleteDepartment(Long departmentId) {
		try {
			departmentRepository.deleteById(departmentId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}
	
	
	public List<Department> allDepartment() {
		return departmentRepository.findAll();
	}
}
