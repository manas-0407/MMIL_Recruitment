package org.mmil.Recruitment_MMIL.controller;

import org.mmil.Recruitment_MMIL.models.RegistrationDto;
import org.mmil.Recruitment_MMIL.service.DB_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class RouteController {

    @Autowired
    DB_Service db_service;

    @GetMapping("/delete_all")
    ResponseEntity<String> deleteAll(){
        db_service.clearAll();
        String failure = "Database Cleared !";
        return ResponseEntity.status(HttpStatus.OK).body("{\n\"message\": \""+ failure + "\"\n}");
    }
    @GetMapping(value = "/excel")
    public ResponseEntity<Resource> downloadExcel() throws IOException {

        String filename = "registration.xlsx";
        ByteArrayInputStream data = db_service.getExcel();
        InputStreamResource file = new InputStreamResource(data);

        ResponseEntity<Resource> response = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION , "attachment; filename="+filename)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(file);

        return response;
    }

    @PostMapping(value = "/registration")
    public String recruitment_Register(@RequestBody RegistrationDto registration_dto){

        return db_service.recruitment_registration(registration_dto);
    }
}
