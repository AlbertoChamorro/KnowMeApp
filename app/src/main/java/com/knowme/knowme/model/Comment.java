package com.knowme.knowme.model;

import java.util.Date;

/**
 * Created by coredeveloper on 5/3/18.
 */

public class Comment {

    private Integer id;
    private String content;
    private Date createdDate;
    private User user;

    public Comment(){}

    public Comment(Integer id,String content,Date createdDate,User user) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
