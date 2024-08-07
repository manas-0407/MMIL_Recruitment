package org.mmil.Recruitment_MMIL.models;


import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Student {

    @NonNull
    String name;

    @NonNull
    String email;

    @NonNull
    String password;

    String role;
}
