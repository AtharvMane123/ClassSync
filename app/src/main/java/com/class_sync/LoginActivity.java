package com.class_sync;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    CardView login_btn;

    TextView register;
    EditText email_editText, password_editText;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor sharedPreferencesEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        login_btn = findViewById(R.id.Login_cardView);
        register = findViewById(R.id.Go_to_register_fom);
        email_editText = findViewById(R.id.Email_EditText);
        password_editText = findViewById(R.id.Password_EditText);



        //sharedPreference is used to check whether user was previously logged in or not
        sharedPreferences = getSharedPreferences("userLoggedIn", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();


        if (sharedPreferences.getBoolean("login", false)) {
            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
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


                            sharedPreferencesEditor.putBoolean("login", true);
                            sharedPreferencesEditor.putBoolean("login", true);
                            sharedPreferencesEditor.putString("Email", Email);
                            sharedPreferencesEditor.putString("Name", Name);
                            sharedPreferencesEditor.commit();

                            checkSharedPreferences("Login");
                            Intent intent = new Intent(LoginActivity.this, HomeScreen.class);
                            startActivity(intent);
                            finish();

                            break;
                        } else {
                            email_editText.setError(null);
                            password_editText.setError("INCORRECT PASSWORD");
                            break;
                        }
                    } else {
                        email_editText.setError("User Not Found");
                        continue;
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
}