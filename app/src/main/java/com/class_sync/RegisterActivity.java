package com.class_sync;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextView login;
    EditText Name, Email, Password, ConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name = findViewById(R.id.Register_Name_EditText);
        Email = findViewById(R.id.Register_Email_EditText);
        Password = findViewById(R.id.Register_Password_EditText);
        ConfirmPassword = findViewById(R.id.Register_ConfirmPassword_EditText);
        login = findViewById(R.id.Go_to_login_fom);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               startActivityWithTransition(getApplicationContext(), LoginActivity.class);
                check_empty_fields();
            }
        });


    }

    public void startActivityWithTransition(Context context, Class c) {
        // Start the new activity with custom transition animations
        Intent intent = new Intent(context, c);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void check_empty_fields() {
        if (TextUtils.isEmpty(Name.getText().toString())) {
            Name.setError("Name field Cannot be Empty");
        } else if (TextUtils.isEmpty(Email.getText().toString())) {
            Email.setError("Email field Cannot be Empty");
        } else if (TextUtils.isEmpty(Password.getText().toString())) {
            Password.setError("Password field Cannot be Empty");
        } else if (TextUtils.isEmpty(ConfirmPassword.getText().toString())) {
            ConfirmPassword.setError("Password field Cannot be Empty");
        } else {
            if (Password.getText().toString().equals(ConfirmPassword.getText().toString())) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                helper_database_signup help=new helper_database_signup(Email.getText().toString(),Name.getText().toString(),Password.getText().toString());
                databaseReference.child("users").child(Name.getText().toString()).setValue(help);
                Intent intent=new Intent(getApplicationContext(),HomeScreen.class);
                startActivity(intent);
                finish();
            } else {
                        ConfirmPassword.setError("Password doesn't matchedhelper_database_signup");
            }


        }
    }
}