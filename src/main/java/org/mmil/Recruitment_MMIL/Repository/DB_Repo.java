package org.mmil.Recruitment_MMIL.Repository;

import org.mmil.Recruitment_MMIL.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DB_Repo extends MongoRepository<Student, Integer> {

    Student findByEmail(String email_id);

    void deleteByRole(String role);

}
