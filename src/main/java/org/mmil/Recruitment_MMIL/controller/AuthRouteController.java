package org.mmil.Recruitment_MMIL.controller;

import lombok.extern.slf4j.Slf4j;
import org.mmil.Recruitment_MMIL.Security.JwtGenerator;
import org.mmil.Recruitment_MMIL.models.AuthResponseDto;
import org.mmil.Recruitment_MMIL.models.LoginDto;
import org.mmil.Recruitment_MMIL.models.RegisterDto;
import org.mmil.Recruitment_MMIL.service.DB_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/auth")
public class AuthRouteController {

    @Autowired
    DB_Service db_service;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtGenerator jwtGenerator;

    @PostMapping(value = "/signup")
    ResponseEntity<String> studentRegister(@RequestBody RegisterDto registerDto){

        try{
            if(db_service.findByEmailId(registerDto.getEmail()) != null){
                String userPresent = "Email already Registered";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(userPresent);
            }

            db_service.saveStudent(registerDto);

            String success  = "Registered Successfully";
            return ResponseEntity.ok().body(success);

        }catch (Exception e){
            String wentWrong  = "Something went wrong! Try again";
            return ResponseEntity.ok().body(wentWrong);
        }

    }

    @PostMapping(value = "/login")
    ResponseEntity<AuthResponseDto> userLogin(@RequestBody LoginDto loginDto){

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail()
                            , loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return ResponseEntity.ok().body(new AuthResponseDto(token));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new AuthResponseDto("BAD CREDENTIALS"));
        }
    }

    @GetMapping("/get")
    String getAll(){
        return "Working test";
    }

    @PostMapping(value = "/admin/signup")
    ResponseEntity<String> adminRegister(@RequestBody RegisterDto registerDto){

        try{
            if(db_service.findByEmailId(registerDto.getEmail()) != null){
                String userPresent = "Email already Registered";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(userPresent);
            }

            db_service.saveAdmin(registerDto);

            String success  = "Admin registered Successfully";
            return ResponseEntity.ok().body(success);

        }catch (Exception e){
            String wentWrong  = "Something went wrong! Try again";
            return ResponseEntity.ok().body(wentWrong);
        }

    }
}