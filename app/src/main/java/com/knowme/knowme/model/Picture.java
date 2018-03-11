package com.knowme.knowme.model;

/**
 * Created by BETO on 27/01/2018.
 */

public class Picture {

    private String  pictureUrl;
    private String  userName;
    private String  time;
    private String  likeNumber = "0";

    public Picture(String pictureUrl, String userName, String time, String likeNumber) {
        this.pictureUrl = pictureUrl;
        this.userName = userName;
        this.time = time;
        this.likeNumber = likeNumber;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }
}
