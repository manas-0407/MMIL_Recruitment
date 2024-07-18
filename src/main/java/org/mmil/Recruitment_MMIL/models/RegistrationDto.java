package org.mmil.Recruitment_MMIL.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {

    private String name;
    private String email;
    private long mobile_no;
    private String university_roll;
    private int year;
    private String branch;
}
