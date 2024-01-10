package Home_Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.class_sync.R;


public class OnlineCourse_category extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root =(ViewGroup) inflater.inflate(R.layout.fragment_online_course_category, container, false);



        return root;
    }
}