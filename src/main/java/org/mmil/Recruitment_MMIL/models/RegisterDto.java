package org.mmil.Recruitment_MMIL.models;

import com.mongodb.lang.NonNull;
import lombok.Data;

@Data
public class RegisterDto {

    @NonNull
    String name;

    @NonNull
    String email;

    @NonNull
    String password;

}
