package com.class_sync;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

public class EBookPreview extends Fragment {

    String BkName, BkAuthor, BkImgUrl, BkAudioUrl, BkDescription,EbookUrl;
    TextView bkName, bkAuthor, bkDescription;
    ShapeableImageView bkImg;
    ImageView backButton;
    Button DownloadEbookbtn,ReadEbookbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_ebook_preview, container, false);
        BkName = getArguments().getString("BkName");
        BkAuthor = getArguments().getString("BkAuthor");
        BkImgUrl = getArguments().getString("BkImgUrl");
        BkAudioUrl = getArguments().getString("BkAudioUrl");
        BkDescription = getArguments().getString("BkDescription");
        EbookUrl=getArguments().getString("BkPdfUrl");





        bkName = root.findViewById(R.id.EBookPreviewBkName);
        bkAuthor = root.findViewById(R.id.EBookPreviewBkAuthor);
        bkDescription = root.findViewById(R.id.EBookPreviewBkDescription);
        bkImg = root.findViewById(R.id.EBookPreviewBkImg);
        DownloadEbookbtn=root.findViewById(R.id.EBookPreviewBkDownloadBtn);
        ReadEbookbtn=root.findViewById(R.id.EBookPreviewBkReadBtn);

        DownloadEbookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(EbookUrl));

                startActivity(intent);
            }
        });


        ReadEbookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), PdfViewer.class);
                intent.putExtra("url",EbookUrl);
                startActivity(intent);
            }
        });




        Uri uri = Uri.parse(getArguments().getString("BkImgUrl"));

        bkName.setText(BkName);
        bkAuthor.setText(BkAuthor);
        bkDescription.setText(BkDescription);
        Log.e(TAG, "onCreateView: " + BkAudioUrl);
        Picasso.get().load(uri).error(R.drawable.f_bk2).into(bkImg);


        return root;
    }

}