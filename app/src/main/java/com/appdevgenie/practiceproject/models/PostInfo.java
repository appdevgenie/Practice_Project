package com.appdevgenie.practiceproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostInfo {

    @SerializedName("Name")
    @Expose
    private String first_name;
    @SerializedName("Last Name")
    @Expose
    private String last_name;
    @SerializedName("Email Address")
    @Expose
    private String email;
    @SerializedName("Link to project")
    @Expose
    private String github_link;

    public PostInfo(String first_name, String last_name, String email, String github_link) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.github_link = github_link;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithub_link() {
        return github_link;
    }

    public void setGithub_link(String github_link) {
        this.github_link = github_link;
    }

    @Override
    public String toString() {
        return "PostInfo{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", github_link='" + github_link + '\'' +
                '}';
    }
}
