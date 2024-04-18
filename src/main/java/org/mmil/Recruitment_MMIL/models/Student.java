package org.mmil.Recruitment_MMIL.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.NonNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Student {

    String name;

    String emailId;

    @NonNull
    String phoneNo;

    String password;

    @JsonCreator
    public Student(@JsonProperty("name") String name,
                   @JsonProperty("emailId") String emailId,
                   @JsonProperty("phoneNo") String phoneNo,
                   @JsonProperty("password") String password) {
        this.name = name;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.password = password;
    }
}
