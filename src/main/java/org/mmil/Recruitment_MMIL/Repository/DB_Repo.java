package org.mmil.Recruitment_MMIL.Repository;

import org.mmil.Recruitment_MMIL.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.io.*;

@Repository
public interface DB_Repo extends MongoRepository<Student, Integer> {

    Student findByEmailId(String email_id);

}
