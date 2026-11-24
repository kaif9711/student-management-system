package com.example.crudSpringCoreDemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student
{
    //Jackson can map this JSON into a Student object using Spring MVC's @RequestBody mechanism.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // FOR primary key auto_increment
    private Long id;

    private String name;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    private int age;
    private String email;
    private int rollno;
    private String subject;
    private boolean deleted;

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getRollno()
    {
        return rollno;
    }

    public void setRollno(int rollno)
    {
        this.rollno = rollno;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }
}
