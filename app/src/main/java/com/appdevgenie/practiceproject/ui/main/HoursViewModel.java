package com.appdevgenie.practiceproject.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.appdevgenie.practiceproject.models.Hour;
import com.appdevgenie.practiceproject.service.LearnerDataInterface;
import com.appdevgenie.practiceproject.service.RetrofitInstance;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoursViewModel extends ViewModel {

    public static final String TAG = "HoursViewModel";

    private MutableLiveData<List<Hour>> hoursListMutableLiveData;

    public LiveData<List<Hour>> getHoursInfo() {
        if (hoursListMutableLiveData == null) {
            hoursListMutableLiveData = new MutableLiveData<List<Hour>>();
            loadHoursInfo();
        }
        return hoursListMutableLiveData;
    }

    private void loadHoursInfo() {

        LearnerDataInterface learnerDataInterface = RetrofitInstance.getServiceInterface();
        Call<List<Hour>> hourCall = learnerDataInterface.getLearningHours();

        hourCall.enqueue(new Callback<List<Hour>>() {
            @Override
            public void onResponse(Call<List<Hour>> call, Response<List<Hour>> response) {
                Log.d(TAG, "onResponse:" + response.toString());

                if (response.isSuccessful()) {
                    List<Hour> hourList = response.body();
                    hoursListMutableLiveData.setValue(hourList);
                }
            }

            @Override
            public void onFailure(Call<List<Hour>> call, Throwable t) {
                Log.d(TAG, "onResponse: failed" + t.getMessage());
            }
        });
    }
}