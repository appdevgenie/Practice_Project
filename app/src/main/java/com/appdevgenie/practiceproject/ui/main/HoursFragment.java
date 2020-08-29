package com.appdevgenie.practiceproject.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appdevgenie.practiceproject.R;
import com.appdevgenie.practiceproject.adapters.LearnerHoursRecyclerAdapter;
import com.appdevgenie.practiceproject.models.Hour;
import com.appdevgenie.practiceproject.service.LearnerDataInterface;
import com.appdevgenie.practiceproject.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class HoursFragment extends Fragment {

    public static final String TAG = "HoursFragment";

    private HoursViewModel mViewModel;
    private RecyclerView recyclerView;
    private LearnerHoursRecyclerAdapter adapter;
    private View view;
    private ArrayList<Hour> hours;
    private Context context;

    public static HoursFragment newInstance() {
        return new HoursFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        getRetrofitResponse();
        return view;
    }

    private void getRetrofitResponse() {

        LearnerDataInterface learnerDataInterface = RetrofitInstance.getServiceInterface();
        Call<List<Hour>> hourCall = learnerDataInterface.getLearningHours();

        hourCall.enqueue(new Callback<List<Hour>>() {
            @Override
            public void onResponse(Call<List<Hour>> call, Response<List<Hour>> response) {
                Log.d(TAG, "onResponse:" + response.toString());

                List<Hour> hourList = response.body();
                hours = (ArrayList<Hour>) hourList;
                populateRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Hour>> call, Throwable t) {
                Log.d(TAG, "onResponse: failed" + t.getMessage());
            }
        });
    }

    private void populateRecyclerView() {

        context = getActivity();

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new LearnerHoursRecyclerAdapter(context, hours);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HoursViewModel.class);
        // TODO: Use the ViewModel
    }

}