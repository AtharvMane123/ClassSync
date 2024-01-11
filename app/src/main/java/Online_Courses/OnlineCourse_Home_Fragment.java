package Online_Courses;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.class_sync.R;

import Home_Fragments.OnlineCourse_category;


public class OnlineCourse_Home_Fragment extends Fragment {


    CardView Design_Category,Development_Category,Marketing_Category,Business_Category;

    ImageView Development_img,Design_img,Marketing_img,Business_img;
    TextView t1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root =(ViewGroup) inflater.inflate(R.layout.fragment_online_course__home_, container, false);
        Design_Category = root.findViewById(R.id.Design_Category);
        Development_Category = root.findViewById(R.id.Development_Category);
        Marketing_Category = root.findViewById(R.id.Marketing_Category);
        Business_Category = root.findViewById(R.id.Business_Category);

        t1=root.findViewById(R.id.t1);

        Development_img=root.findViewById(R.id.develop_img);
        Design_img=root.findViewById(R.id.Design_img);
        Marketing_img=root.findViewById(R.id.Marketing_img);
        Business_img=root.findViewById(R.id.Business_img);


        Business_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnlineCourse_category fragmentB = new OnlineCourse_category();
                Bundle bundle = new Bundle();
                bundle.putInt("imageViewId", R.drawable.business); // Pass the ImageView ID
                bundle.putString("transitionName","business");
//                bundle.putString("Text_transitionName","txt");
                fragmentB.setArguments(bundle);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragmentB);
                fragmentTransaction.addSharedElement(Business_img,"business").addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
Marketing_Category.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        OnlineCourse_category fragmentB = new OnlineCourse_category();
        Bundle bundle = new Bundle();
        bundle.putInt("imageViewId", R.drawable.marketing); // Pass the ImageView ID
        bundle.putString("transitionName","marketing");
        fragmentB.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragmentB);
        fragmentTransaction.addSharedElement(Marketing_img,"marketing").addToBackStack(null);
        fragmentTransaction.commit();
    }
});
Design_Category.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        OnlineCourse_category fragmentB = new OnlineCourse_category();
        Bundle bundle = new Bundle();
        bundle.putInt("imageViewId", R.drawable.design); // Pass the ImageView ID
        bundle.putString("transitionName","design");
        fragmentB.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragmentB);
        fragmentTransaction.addSharedElement(Design_img,"design").addToBackStack(null);
        fragmentTransaction.commit();
    }
});
        Development_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OnlineCourse_category fragmentB = new OnlineCourse_category();
                Bundle bundle = new Bundle();
                bundle.putInt("imageViewId", R.drawable.develop);
                bundle.putString("transitionName","example");// Pass the ImageView ID
                fragmentB.setArguments(bundle);


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addSharedElement(Development_img,"example");
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


}