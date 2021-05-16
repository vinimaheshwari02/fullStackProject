package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }
    @Test
    void itShouldCheckEmail() {
        //given
        String email="vinimaheshwari02@gmail.com";
        Student student=new Student(
                "Vini",
                email,
                Gender.FEMALE
        );
        underTest.save(student);
        //when
        int count=underTest.selectExistEmail(email);
        //then
        assertThat(count).isGreaterThan(0);
    }
}