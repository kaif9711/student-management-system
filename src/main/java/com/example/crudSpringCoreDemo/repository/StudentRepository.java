package com.example.crudSpringCoreDemo.repository;

import java.util.List;
import java.util.Optional;

import com.example.crudSpringCoreDemo.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepository extends JpaRepository<Student, Long>
{
Optional<Student> findByIdAndDeletedIsFalse(Long id);

List<Student> findByDeletedIsFalse();
}
