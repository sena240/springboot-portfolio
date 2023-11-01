package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ProjectTag;

public interface ProjectTagRepository extends JpaRepository<ProjectTag, Long> {

}
