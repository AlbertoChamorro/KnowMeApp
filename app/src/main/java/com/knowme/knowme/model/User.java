package com.knowme.knowme.model;

import java.util.Date;
import java.util.List;

/**
 * Created by coredeveloper on 3/17/18.
 */

public class User {

    private Integer id;
    private String email;
    private String name;
    private String userName;
    private String password;
    private Date birthDate;
    private Country country;
    private List<Comment> comments;

    public User() {
    }

    public User(Integer id,String email,String name,String userName,String password,Date birthDate,Country country,List<Comment> comments) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.birthDate = birthDate;
        this.country = country;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
