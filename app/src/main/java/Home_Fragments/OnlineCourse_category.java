package Home_Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.class_sync.R;

import java.util.Random;


public class OnlineCourse_category extends Fragment {

    ImageView img;
    TextView text1, text2, text3;
    CardView python_course_cardview,c2,c3,c4,c5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_online_course_category, container, false);
        img = root.findViewById(R.id.CourseCategory_img);
        text1 = root.findViewById(R.id.text1);
        python_course_cardview = root.findViewById(R.id.Python_Course_cardView);
        c2 = root.findViewById(R.id.Python_Course_cardView2);
        c3 = root.findViewById(R.id.Python_Course_cardView3);
        c4 = root.findViewById(R.id.Python_Course_cardView4);
        c5 = root.findViewById(R.id.Python_Course_cardView5);



        python_course_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri  uri = Uri.parse("https://mega.nz/folder/drVRQZII#IDpdcIpcMB10MvM4SofgCA");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));

            }
        });
//        text2 = root.findViewById(R.id.text2);
//        text3 = root.findViewById(R.id.text3);
        Random r = new Random();
        int random_num = r.nextInt(2) + 1;


        Animation animation_right = AnimationUtils.loadAnimation(getActivity(), R.anim.category_text);
        Animation animation_left = AnimationUtils.loadAnimation(getActivity(), R.anim.category_text_left);

        if (random_num == 2) {
            python_course_cardview.startAnimation(animation_right);
            c2.startAnimation(animation_right);
            c3.startAnimation(animation_right);
            c4.startAnimation(animation_right);
            c5.startAnimation(animation_right);
//
//            text2.startAnimation(animation_right);
//            text3.startAnimation(animation_right);

        } else {
            python_course_cardview.startAnimation(animation_left);
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


        return root;
    }
}