package org.mmil.Recruitment_MMIL.service;

import org.mmil.Recruitment_MMIL.Repository.DB_Repo;
import org.mmil.Recruitment_MMIL.Repository.RegistationRepo;
import org.mmil.Recruitment_MMIL.models.RegisterDto;
import org.mmil.Recruitment_MMIL.models.RegistrationDto;
import org.mmil.Recruitment_MMIL.models.Registrations;
import org.mmil.Recruitment_MMIL.models.Student;
import org.mmil.Recruitment_MMIL.utils.GetExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class DB_Service {

    @Autowired
    DB_Repo db_repo;

    @Autowired
    RegistationRepo registationRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    GetExcelData excelData;

    public void saveStudent(RegisterDto registerDto){

        Student student = new Student();
        student.setName(registerDto.getName());
        student.setEmail(registerDto.getEmail());
        student.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        student.setRole("STUDENT");

        db_repo.save(student);
    }

    public void saveAdmin(RegisterDto registerDto){

        Student admin = new Student();
        admin.setName(registerDto.getName());
        admin.setEmail(registerDto.getEmail());
        admin.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        admin.setRole("ADMIN");

        db_repo.save(admin);
    }

    public Student findByEmailId(String email){
        Student student = db_repo.findByEmail(email);
        return student;
    }

    public String recruitment_registration(RegistrationDto dto){

        Registrations registrations = new Registrations();
        registrations.setName(dto.getName());
        registrations.setEmail(dto.getEmail());
        registrations.setYear(dto.getYear());
        registrations.setBranch(dto.getBranch());
        registrations.setUniversity_roll(dto.getUniversity_roll());
        registrations.setMobile_no(dto.getMobile_no());

        registationRepo.save(registrations);
        return "Registered Successfully";
    }

    public ByteArrayInputStream getExcel() throws IOException {

        List<Registrations> list = registationRepo.findAll();

        return excelData.dataToExcel(list);
    }

    public void clearAll(){
        db_repo.deleteByRole("STUDENT");
    }
}
