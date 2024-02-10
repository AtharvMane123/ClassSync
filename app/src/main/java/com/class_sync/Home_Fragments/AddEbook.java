package com.class_sync.Home_Fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.class_sync.R;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import com.class_sync.RecyclerViews.RecyclerView_Ebooks_ModelClass;

public class AddEbook extends Fragment {
    EditText name,author,bkImgurl,PdfUrl,description,rating;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    Button submit,Clear;
    String Category="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_add_ebook, container, false);

        name= root.findViewById(R.id.AdminEBooks_BkName);
        author=root.findViewById(R.id.AdminEBooks_AuthorName);
        bkImgurl=root.findViewById(R.id.AdminEBooks_ImgUrl);
        PdfUrl=root.findViewById(R.id.AdminEBooks_PdfUrl);
        description=root.findViewById(R.id.AdminEBooks_Description);
        rating=root.findViewById(R.id.AdminEBooks_Rating);
        submit=root.findViewById(R.id.AdminEBooks_SubmitBtn);
        Clear=root.findViewById(R.id.AdminEBooks_ClearBtn);


        Spinner spinner=(Spinner)root.findViewById(R.id.Adminspinner);
        ArrayList<String> list=new ArrayList<>();
        list.add("Select Category");
        list.add("New");
        list.add("Trending");
        list.add("Bestseller");
        list.add("Business");
        list.add("History");
        list.add("Science");
        list.add("Religion");
        list.add("Sci-Fi");
        list.add("Fantasy");
        list.add("Education");
        list.add("Marketing");
        list.add("Philosophy");
        list.add("Biography");
        list.add("Psychology");
        list.add("Technology");
        list.add("Finance");
        list.add("Programming");
        ArrayAdapter<String > arrayAdapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_multichoice);

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                author.setText("");
                bkImgurl.setText("");
                PdfUrl.setText("");
                description.setText("");
                rating.setText("");
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(), "Please Select the category ", Toast.LENGTH_LONG).show();
            }
        });
        spinner.setAdapter(arrayAdapter);



        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                author.setText("");
                bkImgurl.setText("");
                PdfUrl.setText("");
                description.setText("");
                rating.setText("");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateDetails())
                {
                    if(Category.equals("Select Category"))
                    {
                        Toast.makeText(getContext(), "Please Select the category", Toast.LENGTH_SHORT).show();
                    }else {
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        reference = firebaseDatabase.getReference();


                        RecyclerView_Ebooks_ModelClass modelClass = new RecyclerView_Ebooks_ModelClass(name.getText().toString(), author.getText().toString(), bkImgurl.getText().toString(), PdfUrl.getText().toString(), description.getText().toString(), Category);

                        reference.child("books").child(Category).child(name.getText().toString()).setValue(modelClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getContext(), "EBooks Details Added Succesfully", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }
            }
        });


        return root;
    }

    public Boolean validateDetails()
    {
        if(TextUtils.isEmpty(name.getText().toString()))
        {
            name.setError("Book Name cannot be Empty");
            return  false;
        } else if (TextUtils.isEmpty(author.getText().toString())) {
            author.setError("Author Name cannot be Empty");
            return  false;
        } else if (TextUtils.isEmpty(bkImgurl.getText().toString())) {
            bkImgurl.setError("Img Url cannot be Empty");
            return  false;
        }
        else if (TextUtils.isEmpty(PdfUrl.getText().toString())) {
            PdfUrl.setError("Audio Url cannot be Empty");
            return  false;
        }
        else if (TextUtils.isEmpty(description.getText().toString())) {
            description.setError("Book Description cannot be Empty");
            return  false;
        } else if (TextUtils.isEmpty(rating.getText().toString())) {
            rating.setError("Book Rating cannot be Empty");
            return  false;
        }
        else {
            name.setError(null);
            author.setError(null);
            bkImgurl.setError(null);
            PdfUrl.setError(null);
            description.setError(null);
            rating.setError(null);
            return true;
        }
    }
}