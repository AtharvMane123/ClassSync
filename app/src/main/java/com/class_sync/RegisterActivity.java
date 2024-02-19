package com.class_sync;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextView Go_to_login_page, Register_user_btn;
    EditText Name, Email, Password, ConfirmPassword, StudentRollNo;
    Spinner Spinner_Class;
    String Student_Class;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Name = findViewById(R.id.Register_Name_EditText);
        Email = findViewById(R.id.Register_Email_EditText);
        Password = findViewById(R.id.Register_Password_EditText);
        StudentRollNo = findViewById(R.id.Register_StudentRollNo_EditText);
        ConfirmPassword = findViewById(R.id.Register_ConfirmPassword_EditText);
        Go_to_login_page = findViewById(R.id.Go_to_login_fom);
        Spinner_Class = findViewById(R.id.Register_Spinner_Class);
        Register_user_btn = findViewById(R.id.Register_user_btn);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferences = getSharedPreferences("userLoggedIn", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.Class_Spinner_array,
                android.R.layout.simple_spinner_dropdown_item
        );
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        // Apply the adapter to the spinner
        Spinner_Class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Student_Class = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Please Select the category ", Toast.LENGTH_LONG).show();
            }
        });
        Spinner_Class.setAdapter(adapter);


        Register_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_empty_fields();
            }
        });
        Go_to_login_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityWithTransition(getApplicationContext(), LoginActivity.class);

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
        } else if (TextUtils.isEmpty(StudentRollNo.getText().toString())) {
            StudentRollNo.setError("Password field Cannot be Empty");
        }  else if (Student_Class.equals("Choose your Class")) {
            Toast.makeText(this, "Please Select Your Class", Toast.LENGTH_SHORT).show();
        }else {
            if (Password.getText().toString().equals(ConfirmPassword.getText().toString())) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                DatabaseReference databaseReference_Class = firebaseDatabase.getReference("StudentData");


                databaseReference.child("users").child(Name.getText().toString()).child("name").setValue(Name.getText().toString());
                databaseReference.child("users").child(Name.getText().toString()).child("email").setValue(Email.getText().toString());
                databaseReference.child("users").child(Name.getText().toString()).child("password").setValue(Password.getText().toString());
                databaseReference.child("users").child(Name.getText().toString()).child("rollNo").setValue(StudentRollNo.getText().toString());
                databaseReference.child("users").child(Name.getText().toString()).child("class").setValue(Student_Class);


                databaseReference_Class.child("class").child(Student_Class).child(Name.getText().toString()).child("name").setValue(Name.getText().toString());
                databaseReference_Class.child("class").child(Student_Class).child(Name.getText().toString()).child("email").setValue(Email.getText().toString());
                databaseReference_Class.child("class").child(Student_Class).child(Name.getText().toString()).child("password").setValue(Password.getText().toString());
                databaseReference_Class.child("class").child(Student_Class).child(Name.getText().toString()).child("rollNo").setValue(StudentRollNo.getText().toString());
                databaseReference_Class.child("class").child(Student_Class).child(Name.getText().toString()).child("class").setValue(Student_Class);

                sharedPreferencesEditor.putBoolean("login", true);
                sharedPreferencesEditor.putBoolean("login", true);
                sharedPreferencesEditor.putString("Email", Email.getText().toString());
                sharedPreferencesEditor.putString("Name", Name.getText().toString());
                sharedPreferencesEditor.putString("RollNo", StudentRollNo.getText().toString());
                sharedPreferencesEditor.putString("Class", Student_Class);
                sharedPreferencesEditor.commit();

                Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                intent.putExtra("Register","Register");
                startActivity(intent);
                finish();
            } else {
                ConfirmPassword.setError("Password doesn't matchedhelper_database_signup");
            }


        }
    }
}