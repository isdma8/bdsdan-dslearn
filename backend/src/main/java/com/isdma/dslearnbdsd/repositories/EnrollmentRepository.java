package com.isdma.dslearnbdsd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isdma.dslearnbdsd.entities.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{

}
