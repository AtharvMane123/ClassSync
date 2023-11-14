package Home_Fragments;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.class_sync.EBookPreview;
import com.class_sync.HomeScreen;
import com.class_sync.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import RecyclerViews.RecyclerView_Adapter;
import RecyclerViews.RecyclerView_Ebooks_ModelClass;


public class FinanceEbooks_fragment extends Fragment {


        RecyclerView_Adapter myAdapter;
        RecyclerView recyclerView;
         public static DatabaseReference reference = FirebaseDatabase.getInstance().getReference("books");
        LottieAnimationView progressBar;
        ArrayList<RecyclerView_Ebooks_ModelClass> arrayList=new ArrayList<>();
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_finance_ebooks_fragment, container, false);
            recyclerView=root.findViewById(R.id.HomeFragment_TrendingBooks_recycler);
            progressBar=root.findViewById(R.id.progress);

            HomeScreen.RootRelativeLayout.setBackgroundColor(Color.parseColor("#E3F2FD"));

            arrayList.clear();

            reference.child("Finance").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot snapshot1: snapshot.getChildren())
                    {
                        RecyclerView_Ebooks_ModelClass modelClass=snapshot1.getValue(RecyclerView_Ebooks_ModelClass.class);
                        arrayList.add(modelClass);
                        progressBar.setVisibility(ProgressBar.GONE);
                        reference.child("working").child("1");
                    }myAdapter.notifyDataSetChanged();

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });


            myAdapter=new RecyclerView_Adapter(arrayList, "list", new RecyclerView_Adapter.RecyclerViewItemClickListener() {
                @Override
                public void ItemClick(String BkName, String BkAuthor, String BkImgUrl, String BkPdfUrl, String BkDescription) {

//             getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame,new AudioBookPreview()).commit();

                    Bundle bundle = new Bundle();
                    bundle.putString("BkName", BkName);
                    bundle.putString("BkAuthor", BkAuthor);
                    Log.e(TAG, "ItemClick: " + BkPdfUrl);
                    bundle.putString("BkImgUrl", BkImgUrl);
                    bundle.putString("BkPdfUrl", BkPdfUrl);
                    bundle.putString("BkDescription", BkDescription);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    EBookPreview eBookPreview = new EBookPreview();
                    eBookPreview.setArguments(bundle);
                    fragmentTransaction.replace(R.id.frame, eBookPreview).commit();

                }
            }, new RecyclerView_Adapter.RecyclerViewItemLikedListener() {
                @Override
                public void ItemLiked(String BkName, String BkAuthor, String BkImgUrl, String BkAudioUrl, String BkDescription, String BkRating,int position) {
                    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("users");
                    RecyclerView_Ebooks_ModelClass recyclerView_ebooks_modelClass=new RecyclerView_Ebooks_ModelClass(BkName,BkAuthor,BkImgUrl,BkAudioUrl,BkDescription,arrayList.get(position).getCategory());
                    databaseReference.child(HomeScreen.Name).child("SavedBooks").child(BkName).setValue(recyclerView_ebooks_modelClass);
                    Toast.makeText(getContext(), "Book added as your Favourite books", Toast.LENGTH_LONG).show();

                }
            }, 1);
            recyclerView.setAdapter(myAdapter);
            return root;
        }
}