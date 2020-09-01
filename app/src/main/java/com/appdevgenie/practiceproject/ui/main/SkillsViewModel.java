package com.appdevgenie.practiceproject.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.appdevgenie.practiceproject.models.Skill;
import com.appdevgenie.practiceproject.service.LearnerDataInterface;
import com.appdevgenie.practiceproject.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillsViewModel extends ViewModel {

    public static final String TAG = "SkillsViewModel";

    private MutableLiveData<List<Skill>> skillsListMutableLiveData;

    public LiveData<List<Skill>> getSkillsData() {
        if (skillsListMutableLiveData == null) {
            skillsListMutableLiveData = new MutableLiveData<>();
            loadSkillsInfo();
        }
        return skillsListMutableLiveData;
    }

    private void loadSkillsInfo() {
        LearnerDataInterface learnerDataInterface = RetrofitInstance.getServiceInterface();
        Call<List<Skill>> skillCall = learnerDataInterface.getLearnerSkills();

        skillCall.enqueue(new Callback<List<Skill>>() {
            @Override
            public void onResponse(Call<List<Skill>> call, Response<List<Skill>> response) {
                Log.d(TAG, "onResponse:" + response.toString());
                if (response.isSuccessful()) {
                    List<Skill> skillsList = response.body();
                    skillsListMutableLiveData.setValue(skillsList);
                }
            }

            @Override
            public void onFailure(Call<List<Skill>> call, Throwable t) {
                Log.d(TAG, "onResponse: failed" + t.getMessage());
            }
        });
    }
}