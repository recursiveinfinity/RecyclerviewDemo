package com.example.recyclerviewdemo;

import com.google.gson.annotations.SerializedName;

public class GithubProfile {
    private String login;
    private String name;
    @SerializedName("site_admin")
    private boolean isSiteAdmin;
    @SerializedName("public_repos")
    private int publicRepos;

    public String getLogin() {
        return login;
    }

    public GithubProfile(String login, String name, boolean isSiteAdmin, int publicRepos) {
        this.login = login;
        this.name = name;
        this.isSiteAdmin = isSiteAdmin;
        this.publicRepos = publicRepos;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSiteAdmin() {
        return isSiteAdmin;
    }

    public void setSiteAdmin(boolean siteAdmin) {
        isSiteAdmin = siteAdmin;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }
}
