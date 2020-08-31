package com.appdevgenie.practiceproject;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.appdevgenie.practiceproject.service.LearnerDataInterface;
import com.appdevgenie.practiceproject.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSubmissionActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String FIRST_NAME = "Eugene";
    public static final String LAST_NAME = "Saayman";
    public static final String EMAIL_ADDRESS = "appdevgenie@gmail.com";
    public static final String GITHUB_LINK = "https://github.com/appdevgenie/Practice_Project";

    public static final String TAG = "ProjectSubmission";

    private Context context;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etGithubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_submission);

        context = getApplicationContext();

        ImageButton imageButtonBack = findViewById(R.id.image_button_back);
        imageButtonBack.setOnClickListener(this);
        Button buttonSubmit = findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(this);

        etFirstName = findViewById(R.id.editTextSubmitFirstName);
        etFirstName.setText(FIRST_NAME);
        etLastName = findViewById(R.id.editTextSubmitLastName);
        etLastName.setText(LAST_NAME);
        etEmail = findViewById(R.id.editTextSubmitEmalAddress);
        etEmail.setText(EMAIL_ADDRESS);
        etGithubLink = findViewById(R.id.editTextSubmitGithubLink);
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

                postRetrofitResponse();
                //Toast.makeText(getApplicationContext(), "Posting info", Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();

                //success
                //showResultDialog(R.drawable.ic_tick_green, "Submission successful");

                //unsuccessful
                //showResultDialog(R.drawable.ic_warning_red, "Submission not successful");
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private void postRetrofitResponse() {

        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String email = etEmail.getText().toString();
        String github = etGithubLink.getText().toString();

        LearnerDataInterface learnerDataInterface = RetrofitInstance.postServiceInterface();
        Call<Void> postCall = learnerDataInterface.postPracticeProject(firstName, lastName, email, github);

        postCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                if(response.isSuccessful()){
                    //success
                    showResultDialog(R.drawable.ic_tick_green, "Submission successful");
                }else{
                    //unsuccessful
                    showResultDialog(R.drawable.ic_warning_red, "Submission not successful");
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //unsuccessful
                showResultDialog(R.drawable.ic_warning_red, "Submission not successful");
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void showResultDialog(int imageResource, String message){

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_submit_result);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView tvDialog = dialog.findViewById(R.id.text_view_result);
        tvDialog.setText(message);

        ImageView imageView = dialog.findViewById(R.id.image_view_result);
        imageView.setImageResource(imageResource);

        dialog.show();
    }
}