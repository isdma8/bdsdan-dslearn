package com.isdma.dslearnbdsd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isdma.dslearnbdsd.entities.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long>{

}
