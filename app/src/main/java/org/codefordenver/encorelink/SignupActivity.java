package org.codefordenver.encorelink;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button orgSignup;
    private TextView organizerEmail;
    private TextView organizerPassword;
    public static String userType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        organizerEmail = findViewById(R.id.input_organizer_email);
        organizerPassword = findViewById(R.id.input_organizer_password);
        orgSignup = findViewById(R.id.button_organizer_login);
        mAuth = FirebaseAuth.getInstance();



        orgSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email = organizerEmail.getText().toString().trim();
                String password = organizerPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "Creating new user..." + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressDialog.hide();

                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();

                                } else {
                                    startActivity(new Intent(SignupActivity.this
                                            , CreateOrganizerProfile.class));
                                    finish();
                                }
                            }
                        });

            }


        });

    }

    public void onSwitchClicked(View view) {
        boolean on = view.isPressed();

        if (on) {
            Toast.makeText(this, "Signing up as a Musician", Toast.LENGTH_SHORT).show();
            userType = "musician";

        } else {
            userType = "organizer";
        }
    }
}
