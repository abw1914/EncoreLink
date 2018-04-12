package org.codefordenver.encorelink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private static EditText email;
    private static EditText password;
    private static Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton();
    }

    public void LoginButton() {
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        login_btn = findViewById(R.id.button_login);

        login_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(email.getText().toString().equals("user") &&
                                password.getText().toString().equals("pass")) {
                            Intent intent = new Intent(LoginActivity.this, MainScreenActivity.class);
                            startActivity(intent);

                        }
                    }
                }
        );
    }
}
