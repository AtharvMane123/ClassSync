package com.class_sync.TeacherActivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.class_sync.R;

import org.w3c.dom.Text;

import java.util.Objects;

public class UploadAssignments extends AppCompatActivity {
    Spinner sem,sub;
    WebView webView;
    ProgressBar progressBar;
    TextView textView;
    private  final int FILE_UPLOAD_REQUEST_CODE = 1001;
    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_assignments);
        sem = findViewById(R.id.UploadAssignments_Spinner_Class);
        sub = findViewById(R.id.UploadAssignmnets_Spinner_subject);
        webView = findViewById(R.id.webView);
        textView = findViewById(R.id.text);
        progressBar = findViewById(R.id.UploadAssignMents_progressBar);
        textView.setText("Upload Assignments");
        webView.setBackgroundColor(Color.WHITE);

        sem.setAdapter(setCustomAdapter(R.array.Select_Sem));

        sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(Objects.equals(adapterView.getItemAtPosition(i).toString(), "1st Semester")){
                    sub.setAdapter(setCustomAdapter(R.array.Sem_1));
                }
                else if(Objects.equals(adapterView.getItemAtPosition(i).toString(), "2nd Semester")){
                    sub.setAdapter(setCustomAdapter(R.array.Sem_2));
                }
                else if(Objects.equals(adapterView.getItemAtPosition(i).toString(), "3rd Semester")){
                    sub.setAdapter(setCustomAdapter(R.array.Sem_3));
                }
                else if(Objects.equals(adapterView.getItemAtPosition(i).toString(), "4th Semester")){
                    sub.setAdapter(setCustomAdapter(R.array.Sem_4));
                }
                else if(Objects.equals(adapterView.getItemAtPosition(i).toString(), "5th Semester")){
                    sub.setAdapter(setCustomAdapter(R.array.Sem_5));
                }
                else if(Objects.equals(adapterView.getItemAtPosition(i).toString(), "6th Semester")){
                    sub.setAdapter(setCustomAdapter(R.array.Sem_6));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String SelectedSubject = adapterView.getItemAtPosition(i).toString();

//                First sem
                if(SelectedSubject.equals("ICT Fundamental of ICT.")) {
                    Toast.makeText(UploadAssignments.this, "For now this category is not been created", Toast.LENGTH_SHORT).show();
                    //                    webView.loadUrl("https://mega.nz/filerequest/i9lUwsdh06I");
                }
                else if (SelectedSubject.equals("ENG English.")) {
                    startWebView("Engineering workshop practice");

                } else if (SelectedSubject.equals("BSC Basic Science (Chemistry).")) {
                    startWebView("Engineering graphics(electronics, computer and allied branches)");

                } else if (SelectedSubject.equals("BMS Basic Mathematics.")) {
                    startWebView("https://mega.nz/filerequest/mzWAuvOh-js");

                }


                //second sem
                else if (SelectedSubject.equals( "Applied Mathematics (AMS)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/cjlRzQZI#CRWXIAVOQ7AKcK5M6LU04Q");

                } else if (SelectedSubject.equals("Basic Electrical and Electronics Engineering (BEE)")) {
                    startWebView("https://mega.nz/filerequest/NII4LnbMY8k");

                } else if (SelectedSubject.equals("Programming in C (PIC)")) {
                    startWebView("https://mega.nz/filerequest/tCKvrPfuhQA");

                } else if (SelectedSubject.equals("Web Page Designing (WPD)")) {
                    startWebView("https://mega.nz/filerequest/jlUjlfCyDn8");

                }


                //Third Sem
                else if (SelectedSubject.equals("Object Oriented Programming Using C++(OOP)")) {
                    startWebView("https://mega.nz/filerequest/YPG98jKWVvg");

                } else if (SelectedSubject.equals("Data structure using C Language (DSU)")) {
                    startWebView("https://mega.nz/filerequest/ybieyZ5JLZU");

                } else if (SelectedSubject.equals("Computer Graphics (CGR)")) {
                    startWebView("https://mega.nz/filerequest/wsiOYT1g8Rk");

                } else if (SelectedSubject.equals("Database Management System (DBMS)")) {
                    startWebView("https://mega.nz/filerequest/CU-3O6ZvwBY");

                } else if (SelectedSubject.equals("Digital Techniques (DTE)")) {
                    startWebView("https://mega.nz/filerequest/fjZMlCWplrU");

                }


                //Fourth Sem
                else if (SelectedSubject.equals("Java Programming (JPR)")) {
                    startWebView("https://mega.nz/filerequest/Llit_FvMveE");

                } else if (SelectedSubject.equals("Software Engineering (SEN)")) {
                    startWebView("https://mega.nz/filerequest/bzrP9Ikv2SE");

                } else if (SelectedSubject.equals("Data Communication and Computer Network (DCC)")) {
                    startWebView("https://mega.nz/filerequest/5vOJgIW-rFk");

                } else if (SelectedSubject.equals("Microprocessors (MIC)")) {
                    startWebView("https://mega.nz/filerequest/nyJV7lx1sXw");

                } else if (SelectedSubject.equals("Gui Application Development Using Vb.Net (GAD)")) {
                    startWebView("https://mega.nz/filerequest/t3MOk8LzI9o");

                }




                //Fifth Sem
                else if (SelectedSubject.equals("Advanced Java Programming (AJP)")) {
                    startWebView("https://mega.nz/filerequest/vLVSSMK95SM");

                } else if (SelectedSubject.equals("Software Testing (STE)")) {
                    startWebView("https://mega.nz/filerequest/956wKNW6o8o");

                } else if (SelectedSubject.equals("Advanced Computer Network (ACN)")) {
                    startWebView("https://mega.nz/filerequest/HphF3fA6kG8");

                } else if (SelectedSubject.equals("Operating System (OSY)")) {
                    startWebView("https://mega.nz/filerequest/BjiL8oci0WU");

                } else if (SelectedSubject.equals("Environmental Studies (EST)")) {
                    startWebView("https://mega.nz/filerequest/wY2AMfB8rOw");
                }
//                       //Sixth Sem
                else if (SelectedSubject.equals( "Mobile Application Development (MAD)")) {
                     startWebView("https://mega.nz/filerequest/YgumJVszEc8");
                }
                else if (SelectedSubject.equals( "Emerging Trends in Computer Engineering (ETI)")) {
                    startWebView("https://mega.nz/filerequest/6C1oYjIEQfs");

                }
                else if (SelectedSubject.equals("Programming with Python (PWP)")) {
                    startWebView("https://mega.nz/filerequest/4mX0fJeDfZM");

                }
                else if (SelectedSubject.equals( "Management (MGT)")) {
                    startWebView("https://mega.nz/filerequest/D0RrXs7CpgE");

                }
                else if (SelectedSubject.equals("Network Information Security (NIS)")) {
                    startWebView("https://mega.nz/filerequest/2rto6JPJUTk");

                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });










    }
    ArrayAdapter<CharSequence> setCustomAdapter(int array){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getApplicationContext(),
                array,
                R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    //------------------------------used to Upload Files to the server (Mega Cloud)
    public void startWebView(String URL)
    {progressBar.setVisibility(View.VISIBLE);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
progressBar.setVisibility(View.GONE);
                // Website has finished loading, perform your actions here
                // For example, show a toast or update UI elements
//                Toast.makeText(getApplicationContext(), "Website loaded successfully", Toast.LENGTH_SHORT).show();
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.loadUrl(URL);

        webView.setWebChromeClient(new WebChromeClient() {
            // For 3.0+ Devices (Start)
            // onActivityResult attached before constructor
            protected void openFileChooser(ValueCallback uploadMsg, String acceptType)
            {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            // For Lollipop 5.0+ Devices
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams)
            {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;

                Intent intent = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    intent = fileChooserParams.createIntent();
                }
                try
                {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e)
                {
                    uploadMessage = null;
                    Toast.makeText(getApplicationContext(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }

            //For Android 4.1 only
            protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)
            {
                mUploadMessage = uploadMsg;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("docx/*");
                startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg)
            {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("docx/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
            }
        });

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

                request.setMimeType(mimeType);
                //------------------------COOKIE!!------------------------
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                //------------------------COOKIE!!------------------------
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading file...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimeType));
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
            }
        });
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            view.loadData("Maaf Internet Anda tidak stabil", "text/html", "utf-8");
            super.onReceivedError(view, request, error);
        }

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if (requestCode == REQUEST_SELECT_FILE)
            {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        }
        else if (requestCode == FILECHOOSER_RESULTCODE)
        {
            if (null == mUploadMessage)
                return;
            // Use MainActivity.RESULT_OK if you're implementing WebView inside Fragment
            // Use RESULT_OK only if you're implementing WebView inside an Activity
            Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
        else
            Toast.makeText(getApplicationContext(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
