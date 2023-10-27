package Home_Fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.class_sync.R;


public class EbookFragments extends Fragment {

    View decorView;
    ViewGroup root;
    CardView generalBooks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        root= (ViewGroup) inflater.inflate(R.layout.fragment_ebook_fragments, container, false);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
generalBooks=root.findViewById(R.id.GeneralBooks);
generalBooks.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("null").replace(R.id.frame,new FinanceEbooks_fragment()).commit();
    }
});
        //--------------------------Code to hide Navigation buttons and notification bar-----------------------------------------------------------------------
        decorView = getActivity().getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });
        return root;
    }

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
    //--------------------------Code to hide Navigation buttons and notification bar ends here -----------------------------------------------------------------------

}