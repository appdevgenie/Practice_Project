package com.appdevgenie.practiceproject.service;

import com.appdevgenie.practiceproject.models.Hour;
import com.appdevgenie.practiceproject.models.Skill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LearnerDataInterface {

    //TODO: create constants class in utils
    String FIRST_NAME = "entry.1877115667";
    String LAST_NAME = "entry.2006916086";
    String EMAIL = "entry.1824927963";
    String GITHUB = "entry.284483984";

    @GET("api/hours")
    Call<List<Hour>> getLearningHours();

    @GET("/api/skilliq")
    Call<List<Skill>> getLearnerSkills();

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Void> postPracticeProject(
            @Field(FIRST_NAME) String first_name,
            @Field(LAST_NAME) String last_name,
            @Field(EMAIL) String email,
            @Field(GITHUB) String github_link
    );
}
