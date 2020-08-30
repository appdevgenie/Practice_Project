package com.appdevgenie.practiceproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ProjectSubmissionActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String FIRST_NAME = "Eugene";
    public static final String LAST_NAME = "Saayman";
    public static final String EMAIL_ADDRESS = "appdevgenie@gmail.com";
    public static final String GITHUB_LINK = "https://github.com/appdevgenie/Practice_Project";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_submission);

        ImageButton imageButtonBack = findViewById(R.id.image_button_back);
        imageButtonBack.setOnClickListener(this);
        Button buttonSubmit = findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(this);

        EditText etFirstName = findViewById(R.id.editTextSubmitFirstName);
        etFirstName.setText(FIRST_NAME);
        EditText etLastName = findViewById(R.id.editTextSubmitLastName);
        etLastName.setText(LAST_NAME);
        EditText etEmail = findViewById(R.id.editTextSubmitEmalAddress);
        etEmail.setText(EMAIL_ADDRESS);
        EditText etGithubLink = findViewById(R.id.editTextSubmitGithubLink);
        etGithubLink.setText(GITHUB_LINK);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_button_back:
                finish();
                break;

            case R.id.button_submit:
                //Toast.makeText(this, "submit clicked", Toast.LENGTH_SHORT).show();
                showConfirmDialog();
                break;
        }
    }

    private void showConfirmDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ProjectSubmissionActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_submit_confirm, null);

        Button buttonConfirm = dialogView.findViewById(R.id.button_confirm);
        ImageButton buttonCancel = dialogView.findViewById(R.id.image_button_cancel);

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: POST info with retrofit
                Toast.makeText(getApplicationContext(), "Posting info", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}