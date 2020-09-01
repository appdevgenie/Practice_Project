package com.appdevgenie.practiceproject.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.appdevgenie.practiceproject.R;
import com.appdevgenie.practiceproject.adapters.LearnerSkillsRecyclerAdapter;
import com.appdevgenie.practiceproject.models.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillsFragment extends Fragment {

    public static final String TAG = SkillsFragment.class.getSimpleName();

    private SwipeRefreshLayout swipeRefreshLayout;
    private SkillsViewModel skillsViewModel;
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
        init();
        /*getRetrofitResponse();
        swipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(() -> getRetrofitResponse());*/
        return view;
    }

    private void init() {
        context = getActivity();

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new LearnerSkillsRecyclerAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        observeData();

        swipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                observeData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }



    /*private void getRetrofitResponse() {

        LearnerDataInterface learnerDataInterface = RetrofitInstance.getServiceInterface();
        Call<List<Skill>> skillCall = learnerDataInterface.getLearnerSkills();

        skillCall.enqueue(new Callback<List<Skill>>() {
            @Override
            public void onResponse(Call<List<Skill>> call, Response<List<Skill>> response) {
                Log.d(TAG, "onResponse:" + response.toString());

                swipeRefreshLayout.setRefreshing(false);

                List<Skill> skillList = response.body();
                skills = (ArrayList<Skill>) skillList;
                //populateRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Skill>> call, Throwable t) {
                Log.d(TAG, "onResponse: failed" + t.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }*/

   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        observeData();
    }*/

    private void observeData() {
        skillsViewModel = ViewModelProviders.of(this).get(SkillsViewModel.class);
        skillsViewModel.getSkillsData().observe(getViewLifecycleOwner(), new Observer<List<Skill>>() {
            @Override
            public void onChanged(List<Skill> hours) {
                if (hours != null) {
                    adapter.setSkillArrayList((ArrayList<Skill>) hours);
                    //progressBar.setVisibility(View.GONE);

                }
            }
        });
    }

}