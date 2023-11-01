package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TaskTag;

public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {

}
