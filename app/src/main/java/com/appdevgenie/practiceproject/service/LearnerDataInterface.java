package com.appdevgenie.practiceproject.service;

import com.appdevgenie.practiceproject.models.Hour;
import com.appdevgenie.practiceproject.models.Skill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearnerDataInterface {

    @GET("api/hours")
    Call<List<Hour>> getLearningHours();

    @GET("/api/skilliq")
    Call<List<Skill>> getLearnerSkills();

}
