package org.codefordenver.encorelink;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private static EditText emailText;
    private static EditText passwordText;
    private static Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login();
    }

    public void Login() {
        emailText = findViewById(R.id.input_email);
        passwordText = findViewById(R.id.input_password);
        login_button = findViewById(R.id.button_login);

        login_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "Login");

                        if (!validate()) {
                            onLoginFailed();
                            return;
                        }

                        login_button.setEnabled(false);

                        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage("Authenticating...");
                        progressDialog.show();

                        String email = emailText.getText().toString();
                        String password = passwordText.getText().toString();
                        /**
                         * TODO - Add api authentication here
                         */
                        if (email.equals("user@email.com") && password.equals("pass")) {
                            new Handler().postDelayed(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            onLoginSuccess();
                                            progressDialog.dismiss();
                                        }
                                    }, 3000
                            );
                        }
                    }
                }
        );
    }

    public void onLoginSuccess() {
        login_button.setEnabled(true);
        Intent intent = new Intent(LoginActivity.this, MainScreenActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG);
    }

    public boolean validate() {
        boolean valid = true;
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if  (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("use a password between 4 to 10 characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}
