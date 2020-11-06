package com.isdma.dslearnbdsd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isdma.dslearnbdsd.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
