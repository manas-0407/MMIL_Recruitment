package org.mmil.Recruitment_MMIL.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.mmil.Recruitment_MMIL.models.Student;
import org.mmil.Recruitment_MMIL.service.DB_Service;
import org.mmil.Recruitment_MMIL.service.JwtService;
import org.mmil.Recruitment_MMIL.validators.ValidateStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.io.*;

@Slf4j
@RestController
public class RouteController {

    @Autowired
    PasswordEncoder passwordEncoder;
    // set salt

    @Autowired
    DB_Service db_service;

    @Autowired
    JwtService jwtService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ResponseEntity<String> userRegister(@RequestBody Student student){

        try{
            System.err.println("First");
            if(db_service.findByEmailId(student.getEmailId()) != null){
                String invalidUser = "Already Registered";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\n\"message\": \"" + invalidUser + "\"\n}");
            }

            System.err.println("Second");
            String hashedPass = passwordEncoder.encode(student.getPassword());
            student.setPassword(hashedPass);
            // save to DB
            System.err.println("Eher");
            db_service.save(student);
            String success  = "Registered Successfully";
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\n\"message\": \"" + success + "\"\n}");

        }catch (Exception e){
            String wentWrong  = "Something went wrong ! Try again";
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{" +
                            "\"message\": \"" + wentWrong + "\",\n" +
                            "\"error\": " + e.getMessage() + "\"" +
                            "\n}");
        }

    }

    @PostMapping(value = "/login")
    ResponseEntity<String> userLogin(@RequestBody Student student){

        try{
            Student student1 = db_service.findByEmailId(student.getEmailId());
            if(student1 == null){
                String notPresent = "User Not Found..";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\n\"message\": \""+ notPresent + "\"\n}");
            }

            boolean match = passwordEncoder.matches(student.getPassword() , student1.getPassword());
            if(match){
                // JWT handle
                // this need to Filter handled if authorization is performed
                String token = jwtService.getToken(student.getName());
                String success = "Login Successful..";
                return ResponseEntity.status(HttpStatus.OK).body("{\n" +
                        "\"message\": \"" + success + "\",\n" +
                        "\"token\": " + token + "\"" +
                        "\n}");
            }else{
                String failure = "Invalid Credentials";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\n\"message\": \""+ failure + "\"\n}");
            }
        }catch (Exception exception){
            String failure = "Invalid Credentials";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\n\"message\": \""+ failure + "\"\n}");
        }
    }

    @GetMapping("/get")
    ResponseEntity<List<Student>> getAll(){
        List<Student> all = db_service.getAll();
        System.err.println(all);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/phone_number")
    ResponseEntity<List<Student>> getSortedByNumber(){

        List<Student> phoneNumber = db_service.getSortedByPhone();
        return ResponseEntity.ok(phoneNumber);
    }

    @GetMapping("/delete_all")
    ResponseEntity<String> deleteAll(){
        db_service.clearAll();
        String failure = "Database Cleared !";
        return ResponseEntity.status(HttpStatus.OK).body("{\n\"message\": \""+ failure + "\"\n}");
    }
}