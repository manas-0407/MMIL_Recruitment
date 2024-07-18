package org.mmil.Recruitment_MMIL.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Registrations {

    @NonNull
    String name;

    @NonNull
    String email;

    long mobile_no;

    @NonNull
    String university_roll;

    int year;

    @NonNull
    String branch;

}
