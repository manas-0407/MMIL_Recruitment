package org.mmil.Recruitment_MMIL.service;

import org.mmil.Recruitment_MMIL.Repository.DB_Repo;
import org.mmil.Recruitment_MMIL.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.io.*;

@Service
public class DB_Service {

    @Autowired
    DB_Repo db_repo;

    public void save(Student student){
        db_repo.save(student);
    }

    public Student findByEmailId(String email){
        Student student = db_repo.findByEmailId(email);
        return student;
    }

    public List<Student> getAll(){
        return db_repo.findAll();
    }

}
