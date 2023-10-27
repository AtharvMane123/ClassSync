package com.class_sync;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PdfViewer extends AppCompatActivity {
    PDFView pdfView;
    LottieAnimationView progressBar,pleaseWait;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viwer);
        pdfView = findViewById(R.id.pdfViwer);
        progressBar = findViewById(R.id.progress);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pleaseWait = findViewById(R.id.pleaseWait);

        //Getting Url of the pdf  that has to opened using getStringExtra method of Intent
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        //Calling the DownloadPdfInBackend() constructor to open the pdf
        new DownloadPdfInBackend().execute(url);
    }


    //AsyncTask is used to download the pdf in background and after the pdf is downloaded successfully it will be displayed on PdfView
    private class DownloadPdfInBackend extends AsyncTask<String, Void, InputStream> {
        //The following doInBackground method downloads the pdf in Background
        @Override
        protected InputStream doInBackground(String... strings) {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            InputStream inputStream = null;
            try {
                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return inputStream;
        }


        //The following onPostExecute() method will get execute when the pdf has successfully downloaded in background
        @Override
        protected void onPostExecute(InputStream inputStream) {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

            //Now the pdf will be displayed on the PdfView
            pdfView.fromStream(inputStream)
                    .onLoad(new OnLoadCompleteListener() {
                        @Override
                        public void loadComplete(int nbPages) {
                            progressBar.setVisibility(ProgressBar.GONE);
                            pleaseWait.setVisibility(ProgressBar.GONE);
                            pdfView.setBackgroundColor(Color.parseColor("#171819"));
                        }
                    })
                    .scrollHandle(new DefaultScrollHandle(getApplicationContext()))
                    .autoSpacing(true)
                    .load();
        }
    }
}
