package com.appdevgenie.practiceproject.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appdevgenie.practiceproject.R;
import com.appdevgenie.practiceproject.adapters.LearnerHoursRecyclerAdapter;
import com.appdevgenie.practiceproject.adapters.LearnerSkillsRecyclerAdapter;
import com.appdevgenie.practiceproject.models.Hour;
import com.appdevgenie.practiceproject.models.Skill;
import com.appdevgenie.practiceproject.service.LearnerDataInterface;
import com.appdevgenie.practiceproject.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillsFragment extends Fragment {

    public static final String TAG = "SkillsFragment";

    private SwipeRefreshLayout swipeRefreshLayout;
    private SkillsViewModel mViewModel;
    private RecyclerView recyclerView;
    private LearnerSkillsRecyclerAdapter adapter;
    private View view;
    private ArrayList<Skill> skills;
    private Context context;

    public static SkillsFragment newInstance() {
        return new SkillsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        getRetrofitResponse();
        swipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRetrofitResponse();
            }
        });
        return view;
    }

    private void getRetrofitResponse() {

        LearnerDataInterface learnerDataInterface = RetrofitInstance.getServiceInterface();
        Call<List<Skill>> skillCall = learnerDataInterface.getLearnerSkills();

        skillCall.enqueue(new Callback<List<Skill>>() {
            @Override
            public void onResponse(Call<List<Skill>> call, Response<List<Skill>> response) {
                Log.d(TAG, "onResponse:" + response.toString());

                swipeRefreshLayout.setRefreshing(false);

                List<Skill> hourList = response.body();
                skills = (ArrayList<Skill>) hourList;
                populateRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Skill>> call, Throwable t) {
                Log.d(TAG, "onResponse: failed" + t.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void populateRecyclerView() {

        context = getActivity();

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new LearnerSkillsRecyclerAdapter(context, skills);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SkillsViewModel.class);
        // TODO: Use the ViewModel
    }

}