package com.example.crudSpringCoreDemo.controller;

import java.util.List;

import com.example.crudSpringCoreDemo.entity.Student;
import com.example.crudSpringCoreDemo.service.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/student")
public class StudentController
{

    private StudentService studentService;

    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    // ResponseEntity us usd to explicitly send what HTTP status code to send
    //createStudent method's @RequestBody with hekp of Jackson convert te Postman req into Student object(parameter)
    public ResponseEntity<Student> createStudent(@RequestBody Student student)
    {
        // the object from StudentService.createstudent(student) into Studetn createStudent
        Student createdStudent = studentService.createStudent(student);
        //return 201 http status code when Student Created
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    // to get using id we specify id as paramater
    public ResponseEntity<Student> getStudent(@RequestParam Long id)
    {
        //object of studentService.getStudent(id); is saved in Student studentResp
        Student studentResp = studentService.getStudent(id);
        // if null then return 404
        if (studentResp == null)
        {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            return ResponseEntity.notFound().build();
        }
        // else return 200(and the Student object with code 200
        return ResponseEntity.ok(studentResp);
    }

    @GetMapping("/getAll")
    // here we need to get all so no parameter, since multiple Student objects so we return them in a List
    public ResponseEntity<List<Student>> getAllStudent()
    {
        // save the objects return from studentService.getAllStudent() in the List
        List<Student> studentList = studentService.getAllStudent();
        //if list is empty return 404
        if (studentList.isEmpty())
        {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            return ResponseEntity.notFound().build();
        }
        //else return List(Conaining student object) and 200
        return ResponseEntity.ok(studentList);
    }

    @PutMapping("/update")
    // here update by id require id paramater and update body so pass Student studentReq object as parameter
    public ResponseEntity<Student> updateStudent(@RequestParam Long id, @RequestBody Student studentReq)
    {
        //object studentService.updateStudent(id,studentReq ) is stored in Student studentResp
        Student studentResp = studentService.updateStudent(id, studentReq);
        if (studentResp == null)
        {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            return ResponseEntity.notFound().build();
        }
        //return updated Student object and 200
        return ResponseEntity.ok(studentResp);
    }

    @DeleteMapping("/delete")
    // delete by id so id parameter, return type here will be string
    public ResponseEntity<String> deleteStudent(@RequestParam Long id)
    {
        // delete student (True/False) saved in boolean isDeleted
        Boolean isDeleted = studentService.deleteStudent(id);
        //if not deleted return 404
        if (!isDeleted)
        {
            return ResponseEntity.notFound().build();
        }
        //else return "Record Deleted"
        return ResponseEntity.ok("Record Deleted");
    }

    @PatchMapping("/delete-soft") //this requires PATCH method
    public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id)
    {
        Boolean isDeleted = studentService.deleteStudentSoftly(id);
        if(!isDeleted)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record Deleted softly");
    }

    //@PatchMapping ("/jwdjhb

}
