package org.mmil.Recruitment_MMIL.Security;



import org.mmil.Recruitment_MMIL.Repository.DB_Repo;
import org.mmil.Recruitment_MMIL.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private DB_Repo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = userRepository.findByEmail(username);

        if(student == null){
            throw new UsernameNotFoundException("User not present");
        }

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_"+student.getRole()));

        return new User(student.getEmail() , student.getPassword() , authorityList);
    }

    // User class need username, password , granular authorities as params ,
    // Granular Authorities are a list of authorities a user own for this project assuming no special authorization is there
    // i.e., every user is equal on Authority basis;

}