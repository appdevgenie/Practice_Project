package com.appdevgenie.practiceproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.appdevgenie.practiceproject.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;

public class MainActivity extends AppCompatActivity implements InternetConnectivityListener {

    private ImageView ivNetwork;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ivNetwork = findViewById(R.id.image_view_no_network);
        ivNetwork.setVisibility(View.INVISIBLE);
        buttonSubmit = findViewById(R.id.button_submit);
        buttonSubmit.setVisibility(View.VISIBLE);

        InternetAvailabilityChecker.init(this);
        InternetAvailabilityChecker internetAvailabilityChecker = InternetAvailabilityChecker.getInstance();
        internetAvailabilityChecker.addInternetConnectivityListener(this);

        buttonSubmit.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, ProjectSubmissionActivity.class)));

    }

    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {
        if (isConnected) {
            ivNetwork.setVisibility(View.INVISIBLE);
            buttonSubmit.setVisibility(View.VISIBLE);

            SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
            ViewPager viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);

            TabLayout tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);

        }
        else {
            ivNetwork.setVisibility(View.VISIBLE);
            buttonSubmit.setVisibility(View.INVISIBLE);

            Toast.makeText(this, "Network connection lost!", Toast.LENGTH_SHORT).show();
        }
    }

}