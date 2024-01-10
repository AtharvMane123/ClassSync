package Online_Courses;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.class_sync.R;

import Home_Fragments.OnlineCourse_category;


public class OnlineCourse_Home_Fragment extends Fragment {


    CardView Design_Category,Development_Category,Marketing_Category,Business_Category;
    ImageView img ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root =(ViewGroup) inflater.inflate(R.layout.fragment_online_course__home_, container, false);
        Design_Category = root.findViewById(R.id.Design_Category);
        Development_Category = root.findViewById(R.id.Development_Category);
        Marketing_Category = root.findViewById(R.id.Marketing_Category);
        Business_Category = root.findViewById(R.id.Business_Category);
        img = root.findViewById(R.id.develop_img);



        Development_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),img, ViewCompat.getTransitionName(img));
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new OnlineCourse_category()).commit();
//                startActivity(new Intent(getActivity(), OnlineCourseView.class),optionsCompat.toBundle());





            }
        });

        return root;
    }

}