package com.example.demo.student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path ="api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final  StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents(){

        List<Student>students =studentService.getAllStudents();
        //throw  new IllegalStateException("oops error");
        return students;
    }
    @PostMapping
    public  void addStudent(@Valid @RequestBody Student student){
        try {
            studentService.addStudent(student);
        }catch (Exception e){

        }
    }
}
