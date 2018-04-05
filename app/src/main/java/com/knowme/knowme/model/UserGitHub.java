package com.knowme.knowme.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by coredeveloper on 4/3/18.
 */

public class UserGitHub {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("followers_url")
    private String followersUrl;

    @SerializedName("following_url")
    private String followingUrl;

    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;

    @SerializedName("organizations_url")
    private String organizationsUrl;

    @SerializedName("repos_url")
    private String reposUrl;

    @SerializedName("type")
    private String type;

    @SerializedName("site_admin")
    private Boolean isSiteAdmin;

    public UserGitHub(int id,
                      String name,
                      String fullName,
                      String login,
                      String avatarUrl,
                      String htmlUrl,
                      String followersUrl,
                      String followingUrl,
                      String subscriptionsUrl,
                      String organizationsUrl,
                      String reposUrl,
                      String type,
                      Boolean isSiteAdmin) {

        this.id                 = id;
        this.name               = name;
        this.fullName           = fullName;
        this.login              = login;
        this.avatarUrl          = avatarUrl;
        this.htmlUrl            = htmlUrl;
        this.followersUrl       = followersUrl;
        this.followingUrl       = followingUrl;
        this.subscriptionsUrl   = subscriptionsUrl;
        this.organizationsUrl   = organizationsUrl;
        this.reposUrl           = reposUrl;
        this.type               = type;
        this.isSiteAdmin        = isSiteAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSiteAdmin() {
        return isSiteAdmin;
    }

    public void setSiteAdmin(Boolean siteAdmin) {
        isSiteAdmin = siteAdmin;
    }

    public String getDescriptionLevel(){
        return this.getSiteAdmin() ? "El usuario tiene todos los privilegios asignados" : "El usuario tiene derechos restringidos";
    }
}
