package Home_Fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.class_sync.R;


public class MsbteResources_Fragement extends Fragment {

CardView QuestionPapers,ModelAnswers,SamplePapers;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_msbte_resources__fragement, container, false);;
//     QuestionPapers=root.findViewById(R.id.QuestionPapers_cardview);
        return root;
    }
}