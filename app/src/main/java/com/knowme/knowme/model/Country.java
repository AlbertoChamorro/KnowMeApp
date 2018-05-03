package com.knowme.knowme.model;

import java.util.List;

/**
 * Created by coredeveloper on 5/3/18.
 */

public class Country {

    private Integer id;
    private String name;
    private List<User> users;

    public Country(){}

    public Country(Integer id,String name,List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
