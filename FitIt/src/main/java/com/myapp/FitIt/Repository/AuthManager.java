package com.myapp.FitIt.Repository;

import com.myapp.FitIt.model.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthManager implements AuthenticationProvider {


    @Autowired
    private InstructorRepository instructorRepository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        Instructor asd = instructorRepository.findByUsername(name);
        asd.setRole("USER");

        if(asd == null){
            throw new UsernameNotFoundException("Username not found!");
        }

        if(!passwordEncoder().matches(password,asd.getPassword())){
            throw new BadCredentialsException("Password mismatch!");
        }

        return new UsernamePasswordAuthenticationToken(name,password,asd.getGrantedAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
