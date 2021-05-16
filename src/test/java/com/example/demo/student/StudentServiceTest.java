package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest=new StudentService(studentRepository);
    }

    @Test
    void canGetStudents() {
        //when
        underTest.getAllStudents();

        //then
        verify(studentRepository).findAll();
    }

    @Test
    void canAddStudent() throws Exception {
        //given
        Student student=new Student(
                "Vini",
                "vinimaheshwari02@gmail.com",
                Gender.FEMALE
        );
        //when
        underTest.addStudent(student);

        //then
        ArgumentCaptor<Student>studentArgumentCaptor=ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent=studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }
    @Test
    void canAddStudentWhenEmailExist() throws Exception {
        //given
        Student student=new Student(
                "Vini",
                "vinimaheshwari02@gmail.com",
                Gender.FEMALE
        );
        given(studentRepository.selectExistEmail(student.getEmail()))
                .willReturn(1);

        //when
        assertThatThrownBy(()->underTest.addStudent(student)).hasMessageContaining("Email Already Exist");
        verify(studentRepository,never()).save(any());
    }
}