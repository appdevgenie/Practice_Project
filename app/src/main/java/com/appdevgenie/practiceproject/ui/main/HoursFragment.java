package com.appdevgenie.practiceproject.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.appdevgenie.practiceproject.R;
import com.appdevgenie.practiceproject.adapters.LearnerHoursRecyclerAdapter;
import com.appdevgenie.practiceproject.models.Hour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class HoursFragment extends Fragment {

    public static final String TAG = HoursFragment.class.getSimpleName();

    private SwipeRefreshLayout swipeRefreshLayout;
    private HoursViewModel hoursViewModel;
    private RecyclerView recyclerView;
    private LearnerHoursRecyclerAdapter adapter;
    private View view;
    private ArrayList<Hour> hours;
    private Context context;
    //private ProgressDialog progressDialog;
    private ProgressBar progressBar;

    public static HoursFragment newInstance() {
        return new HoursFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        init();
        return view;
    }

    private void init() {

        context = getActivity();

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        /*progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait...");*/

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new LearnerHoursRecyclerAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
       // getRetrofitResponse();
        observeData();

        swipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //getRetrofitResponse();
                progressBar.setVisibility(View.VISIBLE);
                observeData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /*private void getRetrofitResponse() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        LearnerDataInterface learnerDataInterface = RetrofitInstance.getServiceInterface();
        Call<List<Hour>> hourCall = learnerDataInterface.getLearningHours();

        hourCall.enqueue(new Callback<List<Hour>>() {
            @Override
            public void onResponse(Call<List<Hour>> call, Response<List<Hour>> response) {
                Log.d(TAG, "onResponse:" + response.toString());

                progressDialog.dismiss();
                //swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful()) {
                    List<Hour> hourList = response.body();
                    hours = (ArrayList<Hour>) hourList;
                    adapter.setHourArrayList(hours);
                    //populateRecyclerView();
                } else {
                    Toast.makeText(getActivity(), "Something went wrong!!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Hour>> call, Throwable t) {
                Log.d(TAG, "onResponse: failed" + t.getMessage());
                progressDialog.dismiss();
                //swipeRefreshLayout.setRefreshing(false);
            }
        });

    }*/

    /*private void initRecyclerView() {

        context = getActivity();

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new LearnerHoursRecyclerAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }*/

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //viewModel = ViewModelProviders.of(this).get(HoursViewModel.class);

        observeData();
        *//*
        viewModel.getHoursInfo().observe(getViewLifecycleOwner(), new Observer<List<Hour>>() {
            @Override
            public void onChanged(List<Hour> hours) {
                if (hours != null) {
                    adapter.setHourArrayList((ArrayList<Hour>) hours);
                }
            }
        });*//*
    }*/

    private void observeData() {

        //progressDialog.show();

        hoursViewModel = ViewModelProviders.of(this).get(HoursViewModel.class);
        hoursViewModel.getHoursInfo().observe(getViewLifecycleOwner(), new Observer<List<Hour>>() {
            @Override
            public void onChanged(List<Hour> hours) {
                if (hours != null) {
                   Collections.sort(hours, new Comparator<Hour>() {
                       @Override
                       public int compare(Hour h1, Hour h2) {
                           return (h2.getHours() - h1.getHours());
                       }
                   });
                    adapter.setHourArrayList((ArrayList<Hour>) hours);
                    progressBar.setVisibility(View.GONE);
                    //progressDialog.dismiss();
                }
            }
        });



    }

}