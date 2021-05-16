package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository
        extends JpaRepository<Student,Long> {

    @Query("select  count(s) from Student s where s.email=?1")
    Integer selectExistEmail(String email);
}
