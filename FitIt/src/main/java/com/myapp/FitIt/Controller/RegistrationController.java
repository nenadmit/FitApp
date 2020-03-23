package com.myapp.FitIt.Controller;


import com.myapp.FitIt.Repository.InstructorRepository;
import com.myapp.FitIt.model.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/register")
public class RegistrationController {


    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String getRegistration(){


        return "registration";
    }

    @PostMapping
    public String postRegistration(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String email,
                                   @RequestParam (required = false) boolean accepted,
                                   Model model){

        if (!validateCredentialLength(username)) {
            model.addAttribute("message","Username must me greater that 6 characters!");
            return "registration";
        }else if(!validateCredentialLength(password)){
            model.addAttribute("message","Password must be greater than 6 character");
            return "registration";
        }else if(!validateEmail(email)){
            model.addAttribute("message","Please input a valid email");
            return "registration";
        }else if(!accepted){
            model.addAttribute("message","Please accept Terms & Conditions!");
            return "registration";
        }

        Instructor instructor = new Instructor();
        instructor.setUsername(username);
        instructor.setPassword(passwordEncoder.encode(password));
        instructor.setEmail(email);

        instructorRepository.save(instructor);

        model.addAttribute("message","RegistrationSucessfull");



        return "registration";

    }


    private boolean validateCredentialLength(String credentials){

        if(credentials.length()<6 && credentials.length()>30){
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email) {

        if (email.length() < 0 && email.length()>40) {
            return false;
        }
        for (char c : email.toCharArray()) {
            if (c == '@') {
                return true;
            } }return false;

    }





}

