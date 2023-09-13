package com.spb3.module3.thirdapp.rest;

import com.spb3.module3.thirdapp.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> studentList;

    @PostConstruct
    public void loadData(){
        studentList = new ArrayList<>();

        studentList.add(new Student("Quasim","Rizvi"));
        studentList.add(new Student("Smrthi","Krishnamurthi"));
        studentList.add(new Student("Rahul","Govindkumar"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentList;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
//        Adding the exception logic

        if(studentId >= studentList.size()|| studentId<0){
            throw new StudentNotFoundException("Student Id not found-"+studentId);
        }
        return studentList.get(studentId);
    }
//    Moved this code to Global exception handler
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
////        create a StudentErrorResponse
//        StudentErrorResponse error = new StudentErrorResponse();
//
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setMessage(exc.getMessage());
//        error.setTimestamp(System.currentTimeMillis());
//
//
//        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(Exception exec){
//        StudentErrorResponse error = new StudentErrorResponse();
//
//        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setMessage(exec.getMessage());
//        error.setTimestamp(System.currentTimeMillis());
//
//        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
//    }
}
