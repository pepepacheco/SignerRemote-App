package com.iesvirgendelcarmen.rvargas.touchremote.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iesvirgendelcarmen.rvargas.touchremote.R;
import com.iesvirgendelcarmen.rvargas.touchremote.model.User;
import com.iesvirgendelcarmen.rvargas.touchremote.rest.UserRest;

public class ChangePasswordActivity extends AppCompatActivity {

    String oldPassword;
    String firsNewPassword;
    String secondNewPassword;
    EditText editTextSecondNewPassword;
    User employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.form_change_password);

        employee = (User) getIntent().getParcelableExtra("user");

        Button submit = (Button) findViewById(R.id.change_password_sign_in_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bindData();

                if (isMatchPassword()) {
                    UserRest.changePassword(v, employee.getId(), employee.getOrganizer().getId(), oldPassword, firsNewPassword);
                } else {
                    //TODO: hint err material design
                }
            }


        });
    }

    private void bindData() {
        oldPassword = ((EditText) findViewById(R.id.last_password)).getText().toString();
        firsNewPassword = ((EditText) findViewById(R.id.first_password)).getText().toString();

        editTextSecondNewPassword = (EditText) findViewById(R.id.second_password);
        secondNewPassword = editTextSecondNewPassword.getText().toString();
    }

    private Boolean isMatchPassword() {
        return secondNewPassword.equals(firsNewPassword);
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.finish();
    }
}
