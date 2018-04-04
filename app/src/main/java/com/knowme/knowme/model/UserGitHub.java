package com.knowme.knowme.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by coredeveloper on 4/3/18.
 */

public class UserGitHub {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String fullName;

    public UserGitHub(String id,String name,String fullName) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
