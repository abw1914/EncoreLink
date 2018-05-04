package org.codefordenver.encorelink;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private EditText emailText;
    private EditText passwordText;
    private Button login_button;
    private Button organizer_signup;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    private String userId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.input_email);
        passwordText = findViewById(R.id.input_password);
        login_button = findViewById(R.id.button_login);
        organizer_signup = findViewById(R.id.organizer_signup);









//        if (mAuth.getCurrentUser() != null) {
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            finish();
//        }


        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                final String password = passwordText.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

                //authenticate user
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressDialog.hide();
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        Toast.makeText(LoginActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                                    }
                                }

                                if (task.isSuccessful()){
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        userId = user.getUid();
                                    }


                                    mDataBase = FirebaseDatabase.getInstance().getReference();
                                    mDataBase.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                                                if(dataSnapshot.getKey().equals(CreateMusicianProfile.MUSICIAN_PROFILE) && ds.getKey().equals(userId)) {
                                                    startActivity(new Intent(LoginActivity.this, test.class));
                                                    finish();
                                                    break;
                                                }

                                                if(dataSnapshot.getKey().equals(CreateOrganizerProfile.ORGANIZER_PROFILE) && ds.getKey().equals(userId)) {
                                                    startActivity(new Intent(LoginActivity.this, OrganizerDashboard.class));
                                                    finish();
                                                    break;
                                                }

                                            }
                                        }

                                        @Override
                                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                        }

                                        @Override
                                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                                        }

                                        @Override
                                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }
                        });
            }
        });

        organizer_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //if user is already logged in, go to the appropriate dashboard.
        if(mAuth.getCurrentUser() != null) {


            userId = FirebaseAppOnStartConfiguration.userId;


            mDataBase = FirebaseDatabase.getInstance().getReference();
            mDataBase.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        if(dataSnapshot.getKey().equals(CreateMusicianProfile.MUSICIAN_PROFILE) && ds.getKey().equals(userId)) {
                                startActivity(new Intent(LoginActivity.this, test.class));
                                finish();
                                break;
                        }

                        if(dataSnapshot.getKey().equals(CreateOrganizerProfile.ORGANIZER_PROFILE) && ds.getKey().equals(userId)) {
                            startActivity(new Intent(LoginActivity.this, OrganizerDashboard.class));
                            finish();
                            break;
                        }

                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

    }
}






