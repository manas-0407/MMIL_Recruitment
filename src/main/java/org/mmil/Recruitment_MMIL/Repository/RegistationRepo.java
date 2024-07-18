package org.mmil.Recruitment_MMIL.Repository;

import org.mmil.Recruitment_MMIL.models.Registrations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistationRepo extends MongoRepository<Registrations , String> {
}
