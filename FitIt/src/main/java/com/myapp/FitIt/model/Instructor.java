package com.myapp.FitIt.model;

import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Instructor")
@Table(name="instructors")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="instructor_name")
    private String username;


    @Column(name="instructor_password")
    private String password;


    @Column(name="instructor_email")
    private String email;

    @Column(name="role")
    private String role = "INSTRUCTOR";



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<GrantedAuthority> getGrantedAuthorities(){

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

        ArrayList<GrantedAuthority> list = new ArrayList<>();
        list.add(authority);

        return list;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
