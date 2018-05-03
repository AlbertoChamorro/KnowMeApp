package com.knowme.knowme.model;

/**
 * Created by coredeveloper on 3/17/18.
 */

public class User {

    private Integer id;
    private String email;
    private String name;
    private String userName;
    private String password;

    public User() {
    }

    public User(Integer id,String email,String name,String userName,String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.userName = userName;
        this.password = password;
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
}
