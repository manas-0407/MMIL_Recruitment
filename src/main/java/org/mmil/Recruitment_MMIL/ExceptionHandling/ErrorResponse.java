package org.mmil.Recruitment_MMIL.ExceptionHandling;

public class ErrorResponse {

    public String errorClass;
    public String error;

    ErrorResponse(String errorClass , String error){
        this.error = error;
        this.errorClass = errorClass;
    }
}
