package com.class_sync.TeacherActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

import java.util.Objects;

public class UploadWorkbooks extends AppCompatActivity {
TextView textView;
    Spinner sem,sub;
    private WebView webView;

    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    ProgressBar progressBar;
    private final static int FILECHOOSER_RESULTCODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_assignments);
        textView = findViewById(R.id.text);
        textView.setText("Upload WorkBooks");
        sem = findViewById(R.id.UploadAssignments_Spinner_Class);
        sub = findViewById(R.id.UploadAssignmnets_Spinner_subject);
        webView = findViewById(R.id.webView);
        sem.setAdapter(setCustomAdapter(R.array.Select_Sem));
        progressBar = findViewById(R.id.UploadAssignMents_progressBar);

        webView.setBackgroundColor(Color.WHITE);


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

                //First sem
                if(SelectedSubject.equals("Engineering graphics")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=kmvkZC6PkJuiycHVQSWa1AQETMJ0JKhR7");
                }
                else if (SelectedSubject.equals("ENG English.")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=0mvkZMjhW89MhYO7BUVODmYm037pd9Ru7");

                } else if (SelectedSubject.equals("BSC Basic Science (Physics) and (Chemistry).")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=7mvkZvkcHDtOohOyJfgKHcNgAUuYyPwMX");
                } else if (SelectedSubject.equals("BMS Basic Mathematics.")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=oYvkZyDoyEJI7jf0mxeDDfsoMM8YBWguk");

                }


                //second sem
                else if (SelectedSubject.equals( "Applied Mathematics (AMS)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=FmvkZgNk82YEauObmtA80BYuXnQcSNSO7");

                } else if (SelectedSubject.equals("Basic Electrical and Electronics Engineering (BEE)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=pmvkZNiysCQDjk2VPFagv8qRiqH8MJWOV");

                } else if (SelectedSubject.equals("Programming in C (PIC)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=LmvkZsSj7LcDEvwHAglWXeRxtzfnjR9xk");

                } else if (SelectedSubject.equals("Web Page Designing (WPD)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=4mvkZMtgoH8bA1KX99LwoA8Qjfy0JSAtV");

                }


                //Third Sem
                else if (SelectedSubject.equals("Object Oriented Programming Using C++(OOP)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=bmvkZUqF9BrT2TUzzfPhPwB3S2mlK8ae7");

                } else if (SelectedSubject.equals("Data structure using C Language (DSU)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=QmvkZDHFQgsae6vQh2SUEbnHbdSbn7txV");

                } else if (SelectedSubject.equals("Computer Graphics (CGR)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=8mvkZqyeuX6SJJbQTpFX4HCD3kpPeLz8X");

                } else if (SelectedSubject.equals("Database Management System (DBMS)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=YmvkZXVJuRCnWkE0sOq9wzrUDnpyyAYW7");

                } else if (SelectedSubject.equals("Digital Techniques (DTE)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=mmvkZ1cvGKXJpEe46JVsU9YoUHhH9cbUV");

                }


                //Fourth Sem
                else if (SelectedSubject.equals("Java Programming (JPR)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=umvkZJnq83KOxqBHDRed8tAbWxjY11JuX");

                } else if (SelectedSubject.equals("Software Engineering (SEN)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=BmvkZSn8W2XBouyXAhPwFWeJPfFiIO1TX");

                } else if (SelectedSubject.equals("Data Communication and Computer Network (DCC)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=hmvkZ6UFUiySz9fklTbmJwYCvi0GzzT8k");

                } else if (SelectedSubject.equals("Microprocessors (MIC)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=fmvkZH0oixAdAu4XrLAMQfWbiQhDQOpKy");

                } else if (SelectedSubject.equals("Gui Application Development Using Vb.Net (GAD)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=jmvkZsT1bPmfLEOVAGVPgLoRjpHNBaGCV");

                }




                //Fifth Sem
                else if (SelectedSubject.equals("Advanced Java Programming (AJP)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=WmvkZ7k3P1hgtuG7MfTEggA0UbQyq9Iik");

                } else if (SelectedSubject.equals("Software Testing (STE)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=1mvkZrw5SFgey3afcwitEcXQbD5b0XFcX");

                } else if (SelectedSubject.equals("Advanced Computer Network (ACN)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=2mvkZa43dRV8P3BLTATLIVFSLky2b8uuy");

                } else if (SelectedSubject.equals("Operating System (OSY)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=DmvkZMl2k1ODgLj87PDKHamIRVkEXRhAy");

                } else if (SelectedSubject.equals("Environmental Studies (EST)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=smvkZoDncW6usGKmo4p64Gac5mQ7jKM8y");

                }


////                        //Sixth Sem
               else if(SelectedSubject.equals( "Mobile Application Development (MAD)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=MmvkZhm9hCYMUou42R20jSR5WIjLJAu0y");
                }
                else if (SelectedSubject.equals( "Emerging Trends in Computer Engineering (ETI)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=emvkZOWbR7YnTIOk9vYz7h29E5Xlqrnmy");

                }
                else if (SelectedSubject.equals("Programming with Python (PWP)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=nmvkZEEMMOkEpDbQRl4BsUqEuCJLsKk4y");

                }
                else if (SelectedSubject.equals( "Management (MGT)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=TmvkZDk7PBMe3z8X7oFR6WBdBd0RAwDvk");

                }
                else if (SelectedSubject.equals("Network Information Security (NIS)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=9mvkZTDN10bknuNjzm2IXM3mn2YF1W7mX");

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
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });

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