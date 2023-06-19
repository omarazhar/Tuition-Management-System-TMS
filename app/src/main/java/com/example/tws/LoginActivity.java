package com.example.tws;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isUserNameValid(usernameEditText.getText().toString())) {
                    //loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
                    usernameEditText.setError(getString(R.string.invalid_username));
                    loginButton.setEnabled(false);
                }
                else if (!isPasswordValid(passwordEditText.getText().toString())) {
                    //loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
                    passwordEditText.setError(getString(R.string.invalid_password));
                    loginButton.setEnabled(false);
                } else {
                    //loginFormState.setValue(new LoginFormState(true));
                    loginButton.setEnabled(true);

                }
            }
        };


        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);

                login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        //Result<LoggedInUser> result = loginRepository.login(username, password);

//        if (result instanceof Result.Success) {
//            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
//            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
//        } else {
//            loginResult.setValue(new LoginResult(R.string.login_failed));
//        }

        if (username.equals("omarazhar@gmail.com") && password.equals("123456") )
        {
            //Start new activity
            Intent intent = new Intent(getApplicationContext(), DashboardUi.class);
            getApplicationContext().startActivity(intent);
        }
        else if (username.equals("danielgomes@gmail.com") && password.equals("1234567")){
            Intent intent = new Intent(getApplicationContext(), StudentDashboard.class);
            getApplicationContext().startActivity(intent);
        }

        else {
            Toast.makeText(getApplicationContext(),"Incorrect Credentials",Toast.LENGTH_SHORT).show();
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null || !username.contains("@")) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}