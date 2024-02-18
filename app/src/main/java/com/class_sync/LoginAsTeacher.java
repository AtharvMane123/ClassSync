package com.class_sync;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.class_sync.RecyclerViews.Notification_ModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class LoginAsTeacher extends AppCompatActivity {
EditText PhoneNumber_EditText,Password_EditText,OTP_EditText;
TextView SendOTP,LoginAsStudent;
CardView login;
FirebaseAuth auth = FirebaseAuth.getInstance();
ProgressBar progressBar;

Long timeoutSeconds = 60L;
String phoneNumber;
String verificationCode;
PhoneAuthProvider.ForceResendingToken resendingToken;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor sharedPreferencesEditor;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as_teacher);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LoginAsStudent = findViewById(R.id.LoginAsStudent);
        PhoneNumber_EditText = findViewById(R.id.TeacherPhone_EditText);
        Password_EditText = findViewById(R.id.TeacherPassword_EditText);
        OTP_EditText = findViewById(R.id.TeacherOTP_EditText);
        SendOTP = findViewById(R.id.sendOtp);
        login = findViewById(R.id.TeacherLogin_cardView);
        progressBar = findViewById(R.id.LoginAsTeacher_ProgressBar);


        databaseReference = FirebaseDatabase.getInstance().getReference("Teachers Data");

        LoginActivity.sharedPreferences = getSharedPreferences("TeacherLoggedIn", MODE_PRIVATE);
        LoginActivity.sharedPreferencesEditor = LoginActivity.sharedPreferences.edit();

        sharedPreferences = getSharedPreferences("TeacherLoggedIn", MODE_PRIVATE);
        sharedPreferencesEditor = LoginActivity.sharedPreferences.edit();

        if (sharedPreferences.getBoolean("Teacherlogin", false)) {
            startActivity(new Intent(getApplicationContext(), TeacherHomeScreen.class));
            finish();
        }
        if (LoginActivity.sharedPreferences.getBoolean("Teacherlogin", false)) {
            startActivity(new Intent(getApplicationContext(), TeacherHomeScreen.class));
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            if((snapshot1.child("phone").getValue(Long.class).toString()).equals(PhoneNumber_EditText.getText().toString()))
                            {
                                PhoneNumber_EditText.setError(null);
                                if(snapshot1.child("pass").getValue(String.class).equals(Password_EditText.getText().toString()))
                                {
                                    Toast.makeText(LoginAsTeacher.this, "You are Teacher", Toast.LENGTH_SHORT).show();
                                    String enteredOTP = OTP_EditText.getText().toString();
                                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode,enteredOTP);
                                    signIn(credential);

                                }
                                else {
                                    Password_EditText.setError("Incorrect Password");
                                }
                            }
                            else if(TextUtils.isEmpty(PhoneNumber_EditText.getText().toString())){
                                PhoneNumber_EditText.setError("Phone Number Cannot be Empty");
                            }
                            else if(TextUtils.isEmpty(Password_EditText.getText().toString())){
                                PhoneNumber_EditText.setError("Password field is empty");
                            }
                            else {
                                PhoneNumber_EditText.setError("Incorrect Phone Number");

                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        SendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            if((snapshot1.child("phone").getValue(Long.class).toString()).equals(PhoneNumber_EditText.getText().toString()))
                            {
                                PhoneNumber_EditText.setError(null);
                                if(snapshot1.child("pass").getValue(String.class).equals(Password_EditText.getText().toString()))
                                {
//                                    Toast.makeText(LoginAsTeacher.this, "You are Teacher", Toast.LENGTH_SHORT).show();
                                    sendOtp(PhoneNumber_EditText.getText().toString()  ,false);
                                    progressBar.setVisibility(View.VISIBLE);
                                }
                                else {
                                    Password_EditText.setError("Incorrect Password");
                                }
                            }
                            else if(TextUtils.isEmpty(PhoneNumber_EditText.getText().toString())){
                                PhoneNumber_EditText.setError("Phone Number Cannot be Empty");
                            }
                            else if(TextUtils.isEmpty(Password_EditText.getText().toString())){
                                PhoneNumber_EditText.setError("Password field is empty");
                            }
                            else {
                                PhoneNumber_EditText.setError("Incorrect Phone Number");

                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

    LoginAsStudent.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }
    });
    }
   void sendOtp(String phoneNumber,Boolean isResend){
       PhoneAuthOptions.Builder builder =
               PhoneAuthOptions.newBuilder(auth)
                       .setPhoneNumber("+91"+PhoneNumber_EditText.getText().toString())
                       .setTimeout(timeoutSeconds, TimeUnit.SECONDS)
                       .setActivity(this)
                       .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                           @Override
                           public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                               signIn(phoneAuthCredential);
                           }

                           @Override
                           public void onVerificationFailed(@NonNull FirebaseException e) {
                               Toast.makeText(LoginAsTeacher.this, "OTP verification failed", Toast.LENGTH_SHORT).show();
                               progressBar.setVisibility(View.GONE);
                           }

                           @Override
                           public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                               super.onCodeSent(s, forceResendingToken);
                               verificationCode = s;
                               resendingToken = forceResendingToken;
                               Toast.makeText(LoginAsTeacher.this, "OTP sent Successfully", Toast.LENGTH_SHORT).show();
                               OTP_EditText.setVisibility(View.VISIBLE);
                               progressBar.setVisibility(View.GONE);
                           }
                       });
       if(isResend)
       {
           PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
       }
       else {
           PhoneAuthProvider.verifyPhoneNumber(builder.build());
       }
    }

    private void signIn(PhoneAuthCredential phoneAuthCredential) {
        auth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    LoginActivity.sharedPreferencesEditor.putBoolean("Teacherlogin", true);
                    LoginActivity.sharedPreferencesEditor.putBoolean("Teacherlogin", true);
                    LoginActivity.sharedPreferencesEditor.putString("PhoneNumber", PhoneNumber_EditText.getText().toString());
                    LoginActivity.sharedPreferencesEditor.commit();

                    sharedPreferencesEditor.putBoolean("Teacherlogin", true);
                    sharedPreferencesEditor.putBoolean("Teacherlogin", true);
                    sharedPreferencesEditor.putString("PhoneNumber", PhoneNumber_EditText.getText().toString());
                    sharedPreferencesEditor.commit();
                    startActivity(new Intent(getApplicationContext(),TeacherHomeScreen.class));
                    finish();
                 }
                else {
                    Toast.makeText(LoginAsTeacher.this, "OTP verification Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}