package com.class_sync.TeacherActivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.class_sync.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SendImportantNotices extends AppCompatActivity {
EditText NoticeName,NoticeDescription;
Button SendAnnouncements;
DatabaseReference databaseReference;
    private ChildEventListener childEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_important_notices);
        NoticeName = findViewById(R.id.NoticeName);
        NoticeDescription = findViewById(R.id.NoticeDescription);
        SendAnnouncements = findViewById(R.id.SendAnnouncement_btn);
        databaseReference = FirebaseDatabase.getInstance().getReference("ImportantNotice");





        SendAnnouncements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(NoticeName.getText().toString())){ NoticeName.setError("Announcement name cannot be empty");}
                else if( TextUtils.isEmpty(NoticeDescription.getText().toString())){ NoticeDescription.setError("Announcement Description cannot be empty");}
                else {
                    databaseReference.child(NoticeName.getText().toString()).child("NotificationName").setValue(NoticeName.getText().toString());
                    databaseReference.child(NoticeName.getText().toString()).child("NotificationDescription").setValue(NoticeDescription.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(SendImportantNotices.this, "Notice Sended Successfully", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the ChildEventListener when the activity is destroyed to avoid memory leaks
        if (databaseReference != null && childEventListener != null) {
            databaseReference.removeEventListener(childEventListener);
        }
    }
}