package org.mmil.Recruitment_MMIL.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Student {

    String year;

    String name;

    String admNo;

    String branch;

    String emailId;

    String phoneNo;

    String domain;

    String password;

}
