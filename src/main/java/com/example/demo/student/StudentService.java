package com.example.demo.student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student) throws Exception {
        String email=student.getEmail();
        int countExist= studentRepository.selectExistEmail(email);
        if(countExist>0){
            throw new Exception("Email Already Exist");
        }
        studentRepository.save(student);
    }

}
