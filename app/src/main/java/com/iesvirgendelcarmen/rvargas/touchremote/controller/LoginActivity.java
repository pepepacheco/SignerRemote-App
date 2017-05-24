package com.iesvirgendelcarmen.rvargas.touchremote.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.iesvirgendelcarmen.rvargas.touchremote.R;
import com.iesvirgendelcarmen.rvargas.touchremote.rest.UserRest;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        Button submit = (Button) findViewById(R.id.email_sign_in_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCredentials(view);
            }
        });
    }

    private void sendCredentials(View view) {

        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();

        UserRest.loginUser(emailText, passwordText, view);
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.finish();
    }
}

