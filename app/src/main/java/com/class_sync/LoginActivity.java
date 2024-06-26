package com.class_sync;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.cardview.widget.CardView;

import com.class_sync.Utility.NetworkChangedListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    CardView login_btn;
    NetworkChangedListener networkChangedListener = new NetworkChangedListener();

    TextView register,LoginAsTeacher;
    public static String USERNAME ;
    EditText email_editText, password_editText;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor sharedPreferencesEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        login_btn = findViewById(R.id.Login_cardView);
        register = findViewById(R.id.Go_to_register_fom);
        email_editText = findViewById(R.id.Email_EditText);
        password_editText = findViewById(R.id.Password_EditText);
        LoginAsTeacher = findViewById(R.id.LoginAsTeacher);



        //sharedPreference is used to check whether user was previously logged in or not
        sharedPreferences = getSharedPreferences("userLoggedIn", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();


        if (sharedPreferences.getBoolean("login", false)) {
            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
            finish();
        }
        else if (sharedPreferences.getBoolean("Teacherlogin", false)) {
            startActivity(new Intent(getApplicationContext(), TeacherHomeScreen.class));
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To start the activity with Fade animation
                Intent intent = new Intent(getApplicationContext() , RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });
        LoginAsTeacher.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
            startActivity(new Intent(getApplicationContext(),LoginAsTeacher.class));

            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUser();
//                Intent intent = new Intent(getApplicationContext() , HomeScreen.class);
//                startActivity(intent);
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });
    }
    public void checkUser() {
        String email1 = email_editText.getText().toString();
        String password = password_editText.getText().toString();


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String Email = dataSnapshot.child("email").getValue(String.class);
                    String Pass = dataSnapshot.child("password").getValue(String.class);
                    String Name = dataSnapshot.child("name").getValue(String.class);

                    if (email1.equals(Email)) {
                        email_editText.setError(null);
                        if (password.equals(Pass)) {
                            email_editText.setError(null);
                            DatabaseReference  databaseReference1 = FirebaseDatabase.getInstance().getReference("users").child(Name);
                            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    int loginAttempt = snapshot.child("LoginAttempt").getValue(Integer.class);
                                    if(loginAttempt == 1)
                                    {

                                        Toast.makeText(LoginActivity.this, "You have extended your login limits......\nPlease Contact your Class Teacher.....", Toast.LENGTH_LONG).show();

                                    }else {
                                        sharedPreferencesEditor.putBoolean("login", true);
                                        sharedPreferencesEditor.putBoolean("login", true);
                                        sharedPreferencesEditor.putString("Email", Email);
                                        sharedPreferencesEditor.putString("Name", Name);
                                        sharedPreferencesEditor.commit();

                                        checkSharedPreferences("Login");
                                        databaseReference1.child("LoginAttempt").setValue(1);
                                        USERNAME = Name;
                                        Intent intent = new Intent(LoginActivity.this, HomeScreen.class);
                                        email_editText.setError("");
                                        startActivity(intent);


                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            break;
                        }
                        else {
                            email_editText.setError(null);
                            password_editText.setError("INCORRECT PASSWORD");
                            break;
                        }
                    }
                else {
                        email_editText.setError("User Not Found");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public Boolean checkSharedPreferences(String check) {
        if (check.equals("Login")) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangedListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangedListener);
        super.onStop();
    }
}