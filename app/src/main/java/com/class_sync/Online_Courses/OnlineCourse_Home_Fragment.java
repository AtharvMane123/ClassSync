package com.class_sync.Online_Courses;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.class_sync.Home_Fragments.OnlineCourse_category;
import com.class_sync.R;


public class OnlineCourse_Home_Fragment extends Fragment {


    CardView Design_Category, Development_Category, Marketing_Category, Business_Category;

    CardView Android_course, Java_course, Python_course, C_course, Html_course, Flutter_course, Arduino_course;

    ImageView Development_img, Design_img, Marketing_img, Business_img;
    TextView t1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_online_course__home_, container, false);
        Design_Category = root.findViewById(R.id.Design_Category);
        Development_Category = root.findViewById(R.id.Development_Category);
        Marketing_Category = root.findViewById(R.id.Marketing_Category);
        Business_Category = root.findViewById(R.id.Business_Category);


        Android_course = root.findViewById(R.id.OnlineCourse_Android_course);
        Java_course = root.findViewById(R.id.OnlineCourse_Java_course);
        Python_course = root.findViewById(R.id.OnlineCourse_Python_course);
        C_course = root.findViewById(R.id.OnlineCourse_C_course);
        Html_course = root.findViewById(R.id.OnlineCourse_Html_course);
        Flutter_course = root.findViewById(R.id.OnlineCourse_Flutter_course);
        Arduino_course = root.findViewById(R.id.OnlineCourse_Arduino_course);


        t1 = root.findViewById(R.id.t1);

        Development_img = root.findViewById(R.id.develop_img);
        Design_img = root.findViewById(R.id.Design_img);
        Marketing_img = root.findViewById(R.id.Marketing_img);
        Business_img = root.findViewById(R.id.Business_img);

        ClickedOnProgrammingCourses();
        Business_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnlineCourse_category fragmentB = new OnlineCourse_category();
                Bundle bundle = new Bundle();
                bundle.putInt("imageViewId", R.drawable.business); // Pass the ImageView ID
                bundle.putString("transitionName", "business");
//                bundle.putString("Text_transitionName","txt");
                fragmentB.setArguments(bundle);
                OnlineCourse_category.category = 4;
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragmentB);
                fragmentTransaction.addSharedElement(Business_img, "business").addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        Marketing_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnlineCourse_category fragmentB = new OnlineCourse_category();
                Bundle bundle = new Bundle();
                bundle.putInt("imageViewId", R.drawable.marketing); // Pass the ImageView ID
                bundle.putString("transitionName", "marketing");
                fragmentB.setArguments(bundle);
                OnlineCourse_category.category = 3;
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragmentB);
                fragmentTransaction.addSharedElement(Marketing_img, "marketing").addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        Design_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnlineCourse_category fragmentB = new OnlineCourse_category();
                Bundle bundle = new Bundle();
                bundle.putInt("imageViewId", R.drawable.design); // Pass the ImageView ID
                bundle.putString("transitionName", "design");
                fragmentB.setArguments(bundle);
                OnlineCourse_category.category = 1;
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragmentB);
                fragmentTransaction.addSharedElement(Design_img, "design").addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        Development_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OnlineCourse_category fragmentB = new OnlineCourse_category();
                Bundle bundle = new Bundle();
                bundle.putInt("imageViewId", R.drawable.develop);
                bundle.putString("transitionName", "example");// Pass the ImageView ID
                fragmentB.setArguments(bundle);
                OnlineCourse_category.category = 2;

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addSharedElement(Development_img, "example");
                fragmentTransaction.replace(R.id.frame, fragmentB);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


//                getActivity().getSupportFragmentManager().beginTransaction().addSharedElement(Development_img,"example").replace(R.id.frame,new OnlineCourse_category()).addToBackStack(null).commit();

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        }
        return root;
    }

    private void ClickedOnProgrammingCourses() {
        Android_course.setOnClickListener(view -> OpenMegaCloudStorage("https://mega.nz/folder/MylDyCSJ#XRYOKhlI87lIEBgTRG5aLg"));
        Python_course.setOnClickListener(view -> OpenMegaCloudStorage("https://mega.nz/folder/drVRQZII#IDpdcIpcMB10MvM4SofgCA"));
        Java_course.setOnClickListener(view -> OpenMegaCloudStorage("https://mega.nz/folder/Vv0TQI6K#H6CY8yZliHzo8xn7efFDvA"));
        C_course.setOnClickListener(view -> OpenMegaCloudStorage("https://mega.nz/folder/grtRBLwS#VxWVzd7VHGUsrmu5Khyb2w"));
//        Html_course.setOnClickListener(view -> Toast.makeText(context, "This Course is Not Available for now", Toast.LENGTH_SHORT).show());
        Flutter_course.setOnClickListener(view -> OpenMegaCloudStorage("https://mega.nz/folder/xnF2UL4T#fWAh_Hku3y1ZQt44g5G7Cw"));
        Arduino_course.setOnClickListener(view -> OpenMegaCloudStorage("https://mega.nz/folder/UqtFWSra#Gst1n7vL6owU5p8ORIMgLA"));
    }

    public void OpenMegaCloudStorage(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));

    }
}