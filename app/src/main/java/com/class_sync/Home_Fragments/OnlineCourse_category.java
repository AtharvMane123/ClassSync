package com.class_sync.Home_Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.class_sync.R;

import java.util.Random;


public class OnlineCourse_category extends Fragment {

    public static int category = 0;
    ImageView img;
    TextView text1, text2, text3,text4,text5;
    CardView c1, c2,c3,c4,c5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_online_course_category, container, false);
        img = root.findViewById(R.id.CourseCategory_img);
        text1 = root.findViewById(R.id.text1);
        text2 = root.findViewById(R.id.text2);
        text3 = root.findViewById(R.id.text3);
        text4 = root.findViewById(R.id.text4);
        text5 = root.findViewById(R.id.text5);
        c1 = root.findViewById(R.id.Python_Course_cardView);
        c2 = root.findViewById(R.id.Hindi_Python_Course_cardView);
        c3 = root.findViewById(R.id.Python_Course_cardView3);
        c4 = root.findViewById(R.id.Python_Course_cardView4);
        c5 = root.findViewById(R.id.Python_Course_cardView5);
        displayCategoryWiseCourses(category);





        return root;
    }
    private  void displayCategoryWiseCourses(int category) {
        if(category == 1){
            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("https://mega.nz/folder/drVRQZII#IDpdcIpcMB10MvM4SofgCA");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));

                }
            });
            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("https://mega.nz/folder/Mr9lkJ5L#hGEuO95bhMDaS0kESqA9GA");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));

                }
            });
            c3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Sorry this course is not Available for now, Please try again after some days ", Toast.LENGTH_SHORT).show();


                }
            });
            c4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Sorry this course is not Available for now, Please try again after some days ", Toast.LENGTH_SHORT).show();


                }
            });
            c5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Sorry this course is not Available for now, Please try again after some days ", Toast.LENGTH_SHORT).show();

                }
            });
//        text2 = root.findViewById(R.id.text2);
//        text3 = root.findViewById(R.id.text3);
            Random r = new Random();
            int random_num = r.nextInt(2) + 1;


            Animation animation_right = AnimationUtils.loadAnimation(getActivity(), R.anim.category_text);
            Animation animation_left = AnimationUtils.loadAnimation(getActivity(), R.anim.category_text_left);

            if (random_num == 2) {
                c1.startAnimation(animation_right);
                c2.startAnimation(animation_right);
                c3.startAnimation(animation_right);
                c4.startAnimation(animation_right);
                c5.startAnimation(animation_right);
//
//            text2.startAnimation(animation_right);
//            text3.startAnimation(animation_right);

            } else {
                c1.startAnimation(animation_left);
                c2.startAnimation(animation_left);
                c3.startAnimation(animation_left);
                c4.startAnimation(animation_left);
                c5.startAnimation(animation_left);

            }


            Bundle arguments = getArguments();
            if (arguments != null) {
                int resourceId = arguments.getInt("imageViewId", -1);
                String Img_transition_name = arguments.getString("transitionName", "");
//            String text_transition_name = arguments.getString("Text_transitionName","");

                // Do something with the ImageView ID
                if (resourceId != -1) {
                    img.setImageResource(resourceId);
                    ViewCompat.setTransitionName(img, Img_transition_name);
//                ViewCompat.setTransitionName(textView,text_transition_name);
                    // Now you have the reference to the ImageView in Fragment B
                }
            }
        }
        else if (category == 2) {
            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("https://mega.nz/folder/drVRQZII#IDpdcIpcMB10MvM4SofgCA");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));

                }
            });
            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("https://mega.nz/folder/Mr9lkJ5L#hGEuO95bhMDaS0kESqA9GA");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));

                }
            });
            c3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Sorry this course is not Available for now, Please try again after some days ", Toast.LENGTH_SHORT).show();


                }
            });
            c4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Sorry this course is not Available for now, Please try again after some days ", Toast.LENGTH_SHORT).show();


                }
            });
            c5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Sorry this course is not Available for now, Please try again after some days ", Toast.LENGTH_SHORT).show();

                }
            });
//        text2 = root.findViewById(R.id.text2);
//        text3 = root.findViewById(R.id.text3);
            Random r = new Random();
            int random_num = r.nextInt(2) + 1;


            Animation animation_right = AnimationUtils.loadAnimation(getActivity(), R.anim.category_text);
            Animation animation_left = AnimationUtils.loadAnimation(getActivity(), R.anim.category_text_left);

            if (random_num == 2) {
                c1.startAnimation(animation_right);
                c2.startAnimation(animation_right);
                c3.startAnimation(animation_right);
                c4.startAnimation(animation_right);
                c5.startAnimation(animation_right);
//
//            text2.startAnimation(animation_right);
//            text3.startAnimation(animation_right);

            } else {
                c1.startAnimation(animation_left);
                c2.startAnimation(animation_left);
                c3.startAnimation(animation_left);
                c4.startAnimation(animation_left);
                c5.startAnimation(animation_left);

            }


            Bundle arguments = getArguments();
            if (arguments != null) {
                int resourceId = arguments.getInt("imageViewId", -1);
                String Img_transition_name = arguments.getString("transitionName", "");
//            String text_transition_name = arguments.getString("Text_transitionName","");

                // Do something with the ImageView ID
                if (resourceId != -1) {
                    img.setImageResource(resourceId);
                    ViewCompat.setTransitionName(img, Img_transition_name);
//                ViewCompat.setTransitionName(textView,text_transition_name);
                    // Now you have the reference to the ImageView in Fragment B
                }
            }
        } else if (category == 3) {
            text1.setText("Professional Diploma in Advertising & Advertising Management");
            text2.setText("Email Marketing. Increase Sales With Email Marketing!");
            c3.setVisibility(View.GONE);
            c4.setVisibility(View.GONE);
            c5.setVisibility(View.GONE);
            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("https://mega.nz/folder/ci0nDIjQ#LsnsTgxxUfhrvZs34y2L-Q");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));

                }
            });
            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("https://mega.nz/folder/Mr9lkJ5L#hGEuO95bhMDaS0kESqA9GA");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));

                }
            });
//            c3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getActivity(), "Sorry this course is not Available for now, Please try again after some days ", Toast.LENGTH_SHORT).show();
//
//
//                }
//            });
//            c4.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getActivity(), "Sorry this course is not Available for now, Please try again after some days ", Toast.LENGTH_SHORT).show();
//
//
//                }
//            });
//            c5.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getActivity(), "Sorry this course is not Available for now, Please try again after some days ", Toast.LENGTH_SHORT).show();
//
//                }
//            });
//        text2 = root.findViewById(R.id.text2);
//        text3 = root.findViewById(R.id.text3);
            Random r = new Random();
            int random_num = r.nextInt(2) + 1;


            Animation animation_right = AnimationUtils.loadAnimation(getActivity(), R.anim.category_text);
            Animation animation_left = AnimationUtils.loadAnimation(getActivity(), R.anim.category_text_left);

            if (random_num == 2) {
                c1.startAnimation(animation_right);
                c2.startAnimation(animation_right);
                c3.startAnimation(animation_right);
                c4.startAnimation(animation_right);
                c5.startAnimation(animation_right);
//
//            text2.startAnimation(animation_right);
//            text3.startAnimation(animation_right);

            } else {
                c1.startAnimation(animation_left);
                c2.startAnimation(animation_left);
                c3.startAnimation(animation_left);
                c4.startAnimation(animation_left);
                c5.startAnimation(animation_left);

            }


            Bundle arguments = getArguments();
            if (arguments != null) {
                int resourceId = arguments.getInt("imageViewId", -1);
                String Img_transition_name = arguments.getString("transitionName", "");
//            String text_transition_name = arguments.getString("Text_transitionName","");

                // Do something with the ImageView ID
                if (resourceId != -1) {
                    img.setImageResource(resourceId);
                    ViewCompat.setTransitionName(img, Img_transition_name);
//                ViewCompat.setTransitionName(textView,text_transition_name);
                    // Now you have the reference to the ImageView in Fragment B
                }
            }

        } else if (category == 4) {

            text1.setText("The Complete Business Process Re-engineering Master Class");
            text2.setText("Excellence in Business and Personal Communication Skills");
            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Uri uri = Uri.parse("https://mega.nz/folder/l2EhlLQA#pra7OxxHs21xiX6YdY4J1Q");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));

                }
            });
            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("https://mega.nz/folder/p3dwyTJS#LtZpsQgvtpRHu44V6q_NPw");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));

                }
            });
            c3.setVisibility(View.GONE);
            c4.setVisibility(View.GONE);
            c5.setVisibility(View.GONE);
//            c3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getActivity(), "Sorry this course is not Available for now, Please try again after some days ", Toast.LENGTH_SHORT).show();
//
//
//                }
//            });
//            c4.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getActivity(), "Sorry this course is not Available for now, Please try again after some days ", Toast.LENGTH_SHORT).show();
//
//
//                }
//            });
//            c5.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getActivity(), "Sorry this course is not Available for now, Please try again after some days ", Toast.LENGTH_SHORT).show();
//
//                }
//            });

            Random r = new Random();
            int random_num = r.nextInt(2) + 1;

            Animation animation_right = AnimationUtils.loadAnimation(getActivity(), R.anim.category_text);
            Animation animation_left = AnimationUtils.loadAnimation(getActivity(), R.anim.category_text_left);

            if (random_num == 2) {
                c1.startAnimation(animation_right);
                c2.startAnimation(animation_right);
                c3.startAnimation(animation_right);
                c4.startAnimation(animation_right);
                c5.startAnimation(animation_right);
//
//            text2.startAnimation(animation_right);
//            text3.startAnimation(animation_right);

            } else {
                c1.startAnimation(animation_left);
                c2.startAnimation(animation_left);
                c3.startAnimation(animation_left);
                c4.startAnimation(animation_left);
                c5.startAnimation(animation_left);

            }


            Bundle arguments = getArguments();
            if (arguments != null) {
                int resourceId = arguments.getInt("imageViewId", -1);
                String Img_transition_name = arguments.getString("transitionName", "");
//            String text_transition_name = arguments.getString("Text_transitionName","");

                // Do something with the ImageView ID
                if (resourceId != -1) {
                    img.setImageResource(resourceId);
                    ViewCompat.setTransitionName(img, Img_transition_name);
//                ViewCompat.setTransitionName(textView,text_transition_name);
                    // Now you have the reference to the ImageView in Fragment B
                }
            }
        }
    }

}