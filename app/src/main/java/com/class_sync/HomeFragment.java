package com.class_sync;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.class_sync.Home_Fragments.AddEbook;
import com.class_sync.Home_Fragments.Assignmnent_Fragment;
import com.class_sync.Home_Fragments.EbookFragments;
import com.class_sync.Home_Fragments.ImportantAnnouncements;
import com.class_sync.Home_Fragments.MsbteResources_Fragement;
import com.class_sync.Online_Courses.OnlineCourse_Home_Fragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment {
    public static final String ERROR_DETECTED = "No NFC Detected";
    private final static int REQUEST_CODE = 100;


    Spinner subject, TimePeriod, Gender;

    View customDialogView, BiodataForm;
    Button NFC;
    Context context;

    DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReference("users").child(HomeScreen.User_Name).child("Total Attendance").child(getCurrentMonth_with_Year());


    TextView AddEbook, user_Name;
    EditText FatherName, FathersOccupation, FatherMobileNumber, MotherName, MotherMobileNumber, Address;
    CardView MarkAttendance, Android_course, Java_course, Python_course, C_course, Html_course, Flutter_course, Arduino_course;
    int i = 0;
    ViewGroup root;
    View decorView;
    String Subject_name, Time_period;
    ImageView TrackAttendance, MsbteResources, ebooks, online_course, Assignments, ImportantNotification, HomeFrgament_notification;
    FusedLocationProviderClient fusedLocationProviderClient;
    String gender = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        customDialogView = inflater.inflate(R.layout.add_attendance_dialogbox_layout, null);
        BiodataForm = inflater.inflate(R.layout.student_biodata_form_layout, null);


        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
        }

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }


        if (customDialogView.getParent() != null) {
            ((ViewGroup) customDialogView.getParent()).removeView(customDialogView);
        }

        findId();
        ClickedOnProgrammingCourses();
        user_Name.setText(HomeScreen.User_Name);
        ArrayList<String> list = new ArrayList<>();
        list.add("Select Gender");
        list.add("Male");
        list.add("Female");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        Gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(), "Please Select the category ", Toast.LENGTH_LONG).show();
            }
        });
        Gender.setAdapter(arrayAdapter);


//        Open Student BioData form
        if (HomeScreen.BiodataForm == 1) {
            OpenStudentBiodataForm();
        }


        //Runtime permission
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }


        /// ---------------------------------------------Adapter for Subject Spinner------------------------------------------------------------------------
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.TY_Subject,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Subject_name = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(), "Please Select the category ", Toast.LENGTH_LONG).show();
            }
        });
        subject.setAdapter(adapter);

        /// ---------------------------------------------Adapter for TimePeriod Spinner------------------------------------------------------------------------

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.Time_period,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        TimePeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Time_period = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(), "Please Select the category ", Toast.LENGTH_LONG).show();
            }
        });
        TimePeriod.setAdapter(adapter2);

        TrackAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame, new TrackAttendanceFragment()).commit();

            }
        });
        Assignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new Assignmnent_Fragment()).commit();
            }
        });

        context = getActivity();
        HomeScreen.bottomNavigation.show(1, true);
        decorView = getActivity().getWindow().getDecorView();
        HomeScreen.RootRelativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        AddEbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if (i == 10) {
                    getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new AddEbook()).commit();
                }
            }
        });
        ebooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new EbookFragments()).commit();

            }
        });
        ImportantNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new ImportantAnnouncements()).commit();
            }
        });

        online_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new OnlineCourse_Home_Fragment()).commit();
            }
        });
        HomeFrgament_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new ImportantAnnouncements()).commit();
            }
        });
        MarkAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          getLastLocation();
//                showCustomDialog();

            }
        });

        MsbteResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new MsbteResources_Fragement()).commit();
            }
        });


        return root;
    }


    //----------------------Finding Id's of all the components on the Fragments------------------------------------
    void findId() {
        TrackAttendance = root.findViewById(R.id.TrackAttendance_CardView);
        AddEbook = root.findViewById(R.id.t1);
        online_course = root.findViewById(R.id.OnlineCourses);
        user_Name = root.findViewById(R.id.user_name);
        NFC = root.findViewById(R.id.ScanNFc);
        HomeFrgament_notification = root.findViewById(R.id.HomeFragment_notification_logo);
        MarkAttendance = root.findViewById(R.id.MarkAttendance);
        MsbteResources = root.findViewById(R.id.MsbtePapers);
        ebooks = root.findViewById(R.id.Ebooks_imageView);
        Assignments = root.findViewById(R.id.Assignments);
        ImportantNotification = root.findViewById(R.id.Notification);
        subject = customDialogView.findViewById(R.id.AddAttendance_Subject);
        TimePeriod = customDialogView.findViewById(R.id.AddAttendance_TimePeriod);

        Gender = BiodataForm.findViewById(R.id.Gender);
        FatherName = BiodataForm.findViewById(R.id.FatherName);
        FathersOccupation = BiodataForm.findViewById(R.id.FathersOccupation);
        FatherMobileNumber = BiodataForm.findViewById(R.id.FatherMobileNumber);
        MotherName = BiodataForm.findViewById(R.id.MotherName);
        MotherMobileNumber = BiodataForm.findViewById(R.id.MotherMobileNumber);
        Address = BiodataForm.findViewById(R.id.Address);

        Android_course = root.findViewById(R.id.HomeFragment_Android_course);
        Java_course = root.findViewById(R.id.HomeFragment_Java_course);
        Python_course = root.findViewById(R.id.HomeFragment_Python_course);
        C_course = root.findViewById(R.id.HomeFragment_C_course);
        Html_course = root.findViewById(R.id.HomeFragment_Html_course);
        Flutter_course = root.findViewById(R.id.HomeFragment_Flutter_course);
        Arduino_course = root.findViewById(R.id.HomeFragment_Arduino_course);

    }


    //-----------------------------Follwing Methods are used for Location Purpose------------------------
    private void getLastLocation() {
        //To get the last location of the user
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Geocoder geo = new Geocoder(getContext(), Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                                    address.setText("Pune: "+addresses.get(0).getAddressLine(0));
                                    Toast.makeText(getActivity(), "" + addresses.get(0).getAddressLine(0), Toast.LENGTH_LONG).show();
                                    String College_Location_words[] = {"Satar Nagar", "Hadapsar", "Pune", "Autadwadi", "Handewadi"};
                                    if (containsWords(addresses.get(0).getAddressLine(0), College_Location_words)) {
                                        showCustomDialog();
                                    } else {
                                        Toast.makeText(getActivity(), "You are not in the College, so your attendance will not be marked", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

        } else {
            askPermission();

        }


    }

    private void askPermission() {
        //To take the permission from the user to read/get the current location
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        //ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {

                Toast.makeText(getContext(), "Please provide the required permission", Toast.LENGTH_SHORT).show();

            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Location code ends -----------------------------------------------------------------------------------------------------------------------


    }


    //    ----------------------------------------Code to hide navigation buttons-------------------------------------------
    public void onWindowFocusChanged(boolean hasFocus) {
        super.getActivity().onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;


    }


    //--------------------Following Methods are used to verify Users Location and the College Location-------------------
    public static boolean containsWord(String input, String word) {
        // Case-insensitive check for the presence of a word in the string
        return input.toLowerCase().contains(word.toLowerCase());
    }

    public static boolean containsWords(String input, String[] words) {
        // Case-insensitive check for the presence of any of the specified words in the string
        for (String word : words) {
            if (containsWord(input, word)) {
                return true;
            }
        }
        return false;
    }


    //------------------------- Following Method is used to retrive the current date -----------------------------------
    private String getCurrentDate() {
        // Get the current date and time
        Date currentDate = new Date();

        // Format the date using a desired pattern
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(currentDate);
    }

    public String getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();

        // Format the current date to obtain the month abbreviation
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM", Locale.getDefault());
        String currentMonth = dateFormat.format(calendar.getTime());

        return currentMonth;
    }

    // ----------------------------------------------------Dialog Box to Add Attendance ------------------------------------------------------
    private void showCustomDialog() {



        // Build the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(customDialogView);
        builder.setTitle("Add Attendance");
        builder.setCancelable(true);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                incrementAttendanceValue();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                DatabaseReference databaseReference1 = firebaseDatabase.getReference();


                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String Class = snapshot.child("users").child(HomeScreen.User_Name).child("class").getValue(String.class);
                        String Student_roll = snapshot.child("users").child(HomeScreen.User_Name).child("rollNo").getValue(String.class);
                        String TotalAttendance = snapshot.child("users").child("Month").child(getCurrentMonth()).child("TotalAttendance").getValue(String.class);


                        databaseReference.child("Attendance").child(getCurrentDate()).child(Class).child(Time_period).child(Subject_name).child(HomeScreen.User_Name)
                                .child("name").setValue(HomeScreen.User_Name);
                        databaseReference.child("Attendance").child(getCurrentDate()).child(Class).child(Time_period).child(Subject_name).child(HomeScreen.User_Name)
                                .child("rollNo").setValue(Student_roll).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(context, "Attendance has been Marked ", Toast.LENGTH_SHORT).show();
                                        builder.create().dismiss();
                                    }
                                });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                builder.create().cancel();
                builder.create().dismiss();

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle Cancel button click
                builder.create().dismiss();
                builder.create().cancel();
                Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
            }
        });

        // Show the AlertDialog
        AlertDialog customDialog = builder.create();
        // Check if contentView already has a parent


        if (customDialog != null && customDialog.isShowing()) {
            customDialog.dismiss();
            customDialog.cancel();
//            builder.setView(null);
        }

        customDialog.show();
    }

    private void OpenStudentBiodataForm() {
        // Build the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(BiodataForm);
        // Show the AlertDialog

        builder.setCancelable(false);
        builder.setTitle("BioData Form");

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (CheckEmptyFields()) {
                    addBiodata();
                } else {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFragment()).commit();
                    Toast.makeText(context, "PLease enter all the details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog biodata = builder.create();
        biodata.show();

    }


    public Boolean CheckEmptyFields() {
        if (TextUtils.isEmpty(FatherName.getText().toString()) || TextUtils.isEmpty(FathersOccupation.getText().toString()) || TextUtils.isEmpty(FatherMobileNumber.getText().toString()) || TextUtils.isEmpty(MotherName.getText().toString())
                || TextUtils.isEmpty(MotherMobileNumber.getText().toString()) || TextUtils.isEmpty(Address.getText().toString())) {
            FatherName.setError("Please fill all the fields");
            return false;
        } else {
            if (gender.equals("Select Gender")) {
                Toast.makeText(context, "please select  the gender", Toast.LENGTH_SHORT).show();
                return false;
            } else {

                return true;
            }
        }

    }

    public void addBiodata() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("users");


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Class = snapshot.child(HomeScreen.User_Name).child("class").getValue(String.class);
                String Student_roll = snapshot.child("users").child(HomeScreen.User_Name).child("rollNo").getValue(String.class);
                String TotalAttendance = snapshot.child("users").child("Month").child(getCurrentMonth()).child("TotalAttendance").getValue(String.class);
                DatabaseReference databaseReference1 = firebaseDatabase.getReference("StudentData").child("class").child(Class);

                databaseReference.child(HomeScreen.User_Name).child("FatherName").setValue(FatherName.getText().toString());
                databaseReference.child(HomeScreen.User_Name).child("FatherOccupation").setValue(FathersOccupation.getText().toString());
                databaseReference.child(HomeScreen.User_Name).child("FathersMobileNumber").setValue(FatherMobileNumber.getText().toString());
                databaseReference.child(HomeScreen.User_Name).child("MothersName").setValue(MotherName.getText().toString());
                databaseReference.child(HomeScreen.User_Name).child("MothersMobileNumber").setValue(MotherMobileNumber.getText().toString());
                databaseReference.child(HomeScreen.User_Name).child("Address").setValue(Address.getText().toString());
                databaseReference.child(HomeScreen.User_Name).child("gender").setValue(gender);

                databaseReference1.child(HomeScreen.User_Name).child("FatherName").setValue(FatherName.getText().toString());
                databaseReference1.child(HomeScreen.User_Name).child("FatherOccupation").setValue(FathersOccupation.getText().toString());
                databaseReference1.child(HomeScreen.User_Name).child("FathersMobileNumber").setValue(FatherMobileNumber.getText().toString());
                databaseReference1.child(HomeScreen.User_Name).child("MothersName").setValue(MotherName.getText().toString());
                databaseReference1.child(HomeScreen.User_Name).child("MothersMobileNumber").setValue(MotherMobileNumber.getText().toString());
                databaseReference1.child(HomeScreen.User_Name).child("Address").setValue(Address.getText().toString());
                databaseReference1.child(HomeScreen.User_Name).child("gender").setValue(gender);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClickedOnProgrammingCourses() {
        Android_course.setOnClickListener(view -> OpenMegaCloudStorage("https://mega.nz/folder/MylDyCSJ#XRYOKhlI87lIEBgTRG5aLg"));
        Python_course.setOnClickListener(view -> OpenMegaCloudStorage("https://mega.nz/folder/drVRQZII#IDpdcIpcMB10MvM4SofgCA"));
        Java_course.setOnClickListener(view -> OpenMegaCloudStorage("https://mega.nz/folder/Vv0TQI6K#H6CY8yZliHzo8xn7efFDvA"));
        C_course.setOnClickListener(view -> OpenMegaCloudStorage("https://mega.nz/folder/grtRBLwS#VxWVzd7VHGUsrmu5Khyb2w"));
        Html_course.setOnClickListener(view -> Toast.makeText(context, "This Course is Not Available for now", Toast.LENGTH_SHORT).show());
        Flutter_course.setOnClickListener(view -> OpenMegaCloudStorage("https://mega.nz/folder/xnF2UL4T#fWAh_Hku3y1ZQt44g5G7Cw"));
        Arduino_course.setOnClickListener(view -> Toast.makeText(context, "This Course is Not Available for now", Toast.LENGTH_SHORT).show());
    }

    public void OpenMegaCloudStorage(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));

    }
    private String getCurrentMonth_with_Year() {
        Date currentDate = new Date();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        String currentMonth = monthFormat.format(currentDate);
        String currentYear = yearFormat.format(currentDate);
        return currentMonth + "-" + currentYear;
    }
    private void incrementAttendanceValue() {
        databaseReference.child("Total Attendance") // Specify the key of the value you want to increment
                .setValue(ServerValue.increment(1)) // Increment the value by 1
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Value incremented successfully
                        Toast.makeText(getActivity(), "Value incremented successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure
                        Toast.makeText(getActivity(), "Failed to increment value: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}