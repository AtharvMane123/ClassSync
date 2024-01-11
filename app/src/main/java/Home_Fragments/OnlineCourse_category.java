package Home_Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.class_sync.R;

import java.util.Random;


public class OnlineCourse_category extends Fragment {

    ImageView img;
    TextView text1,text2,text3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root =(ViewGroup) inflater.inflate(R.layout.fragment_online_course_category, container, false);
    img = root.findViewById(R.id.CourseCategory_img);
    text1 = root.findViewById(R.id.text1);
    text2 = root.findViewById(R.id.text2);
    text3 = root.findViewById(R.id.text3);

        Random r =new Random();
        int random_num = r.nextInt(2)+1;


        Animation animation_right= AnimationUtils.loadAnimation(getActivity(),R.anim.category_text);
        Animation animation_left= AnimationUtils.loadAnimation(getActivity(),R.anim.category_text_left);

        if(random_num == 2){
            text1.startAnimation(animation_right);
            text2.startAnimation(animation_right);
            text3.startAnimation(animation_right);
        }else {
            text1.startAnimation(animation_left);
            text2.startAnimation(animation_left);
            text3.startAnimation(animation_left);
        }




        Bundle arguments = getArguments();
        if (arguments != null) {
            int resourceId = arguments.getInt("imageViewId", -1);
            String Img_transition_name = arguments.getString("transitionName","");
//            String text_transition_name = arguments.getString("Text_transitionName","");

            // Do something with the ImageView ID
            if (resourceId != -1) {
               img.setImageResource(resourceId);
                ViewCompat.setTransitionName(img,Img_transition_name);
//                ViewCompat.setTransitionName(textView,text_transition_name);
                // Now you have the reference to the ImageView in Fragment B
            }
        }

        //The code written below is used to make a shared Element transition in fragment
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    // Not needed, but you can add custom behavior here
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    // Start postponed enter transition when the shared element is ready
                    startPostponedEnterTransition();
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                    // Not needed, but you can add custom behavior here
                }

                @Override
                public void onTransitionPause(Transition transition) {
                    // Not needed, but you can add custom behavior here
                }

                @Override
                public void onTransitionResume(Transition transition) {
                    // Not needed, but you can add custom behavior here
                }
            });
        }

        return root;
    }
}