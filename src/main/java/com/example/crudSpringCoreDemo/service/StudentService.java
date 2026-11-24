package com.example.crudSpringCoreDemo.service;

import java.util.List;
import java.util.Optional;

import com.example.crudSpringCoreDemo.entity.Student;
import com.example.crudSpringCoreDemo.repository.StudentRepository;

import org.springframework.stereotype.Service;

@Service
public class StudentService
{
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    //used ofr @PostMapping
    public Student createStudent(Student studentReq)
    {
        studentReq.setDeleted(false);// defaulty the isdeleted flag will be false for newly created student
        // business logic
        // calls studentRepository save method and save(create) the Student Object
        Student studentresp = studentRepository.save(studentReq);
        return studentresp;
    }

    //@GetMapping
    //select*from student where id and delete = false
    public Student getStudent(Long id)
    {
        // calls studentRepository findById(id) method to find the id specific student
        //optional used if id is present
        Optional<Student> studentResp = studentRepository.findByIdAndDeletedIsFalse(id);
        //if the id specific student is present , then get
        if (studentResp.isPresent())
        {
            return studentResp.get();
        }
        //else return null
        return null;
    }


    //select * from students where Delete = false;
    public List<Student> getAllStudent()
    {
        // calls studentRepository findAll(id) method to get all the Student Objects
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList;
    }



    //Update * from student where id and delete = false ( to update non-soft deleted student objects)
    // tales two paramater id and Student StudentReq(since we need to update the Student Object)
    public Student updateStudent(Long id, Student StudentReq)
    {
        // calls studentRepository findById(id) method to check if the id specific Student is present
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        //if empty , return null
        if (existingStudent.isEmpty())
        {
            return null;
        }
        // else, edit/set new Id specific Student Object records
        Student studentToSave = existingStudent.get();

        studentToSave.setName(StudentReq.getName());
        studentToSave.setRollno(StudentReq.getRollno());
        studentToSave.setSubject(StudentReq.getSubject());
        studentToSave.setEmail(StudentReq.getEmail());
        studentToSave.setAge(StudentReq.getAge());
        // then save it again
        studentToSave.setDeleted(false);
        return studentRepository.save(studentToSave);

    }
    //delete * from student where id
    public Boolean deleteStudent(Long id)
    {
        // calls studentRepository existsById(id) method to check if the id specific Student is present
        Boolean isStudent = studentRepository.existsById(id);
        if (!isStudent)
            return false;
        // then delete
        studentRepository.deleteById(id);
        return true;
    }


    //softdelete * from Student where id
    public Boolean deleteStudentSoftly(Long id)
    {
        // first we will get the student with id and whose Deleted = false;
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        // check if its empty
        if(existingStudent.isEmpty())
        {
            return null;
        }
        //else, we will get it and save the student into studentToSave
        Student studentToSave = existingStudent.get();
        //now we change the setDelete to true(basically soft delete)
        studentToSave.setDeleted(true);
        //and save
        studentRepository.save(studentToSave);

        return true;
    }
}
