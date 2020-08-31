package com.appdevgenie.practiceproject.ui.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

    private SwipeRefreshLayout swipeRefreshLayout;
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

        context = getActivity();

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
                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful()) {
                    List<Hour> hourList = response.body();
                    hours = (ArrayList<Hour>) hourList;
                    populateRecyclerView();
                } else {
                    Toast.makeText(getActivity(), "Something went wrong!!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Hour>> call, Throwable t) {
                Log.d(TAG, "onResponse: failed" + t.getMessage());
                progressDialog.dismiss();
                swipeRefreshLayout.setRefreshing(false);
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