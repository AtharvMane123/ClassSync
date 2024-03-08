package com.class_sync.Home_Fragments;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.DOWNLOAD_SERVICE;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.class_sync.R;

import java.net.URLConnection;
import java.util.Objects;


public class Workbooks_Fragment extends Fragment {

    Spinner sem,sub;
    WebView webView;
    ProgressBar progressBar;
    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_workbooks_, container, false);
        sem = root.findViewById(R.id.ViewWorkbooks_Spinner_Class);
        sub = root.findViewById(R.id.ViewWorkbooks_Spinner_subject);
        webView = root.findViewById(R.id.ViewWorkbooks_webView);
        progressBar = root.findViewById(R.id.ViewWorkbooks_progressBar);


        sem.setAdapter(setCustomAdapter(R.array.Select_Sem));
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

//                First sem
                if(SelectedSubject.equals("Engineering graphics")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZ4qRF0ZwcCazPDg1OYY6eainjGlPuCUCebV");
                }
                else if (SelectedSubject.equals("ENG English.")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZztRF0Z1tS5jy1jbWJFBMptFEm4MQLtOryk");

                } else if (SelectedSubject.equals("BSC Basic Science (Physics) and (Chemistry).")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZvdRF0ZkArsSU4cfK5kAtRQv0VoMmRm11gk");

                } else if (SelectedSubject.equals("BMS Basic Mathematics.")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZkdRF0Z6cOMLsu3NSFU13Vk7d1kY56hqTOX");

                }


                //second sem
                else if (SelectedSubject.equals( "Applied Mathematics (AMS)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZNtRF0ZgxEpx67ssnX5aN3t1FkM3XwtlYpX");

                } else if (SelectedSubject.equals("Basic Electrical and Electronics Engineering (BEE)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZr6RF0ZtpyuICntyz5mK0s5oEn8Cy737cfk");

                } else if (SelectedSubject.equals("Programming in C (PIC)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZeERF0ZdW1TjJOU2yVFOVlA3DH62fSDgX6y");

                } else if (SelectedSubject.equals("Web Page Designing (WPD)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZlERF0ZSHO8H6gt8H7RUvBPmzjT0YcJlIxX");

                }
                else if (SelectedSubject.equals("Linux Basics")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZpERF0ZUHqAXOGWz8BVvONPqCezDJqmne27");
                }


                //Third Sem
                else if (SelectedSubject.equals("Object Oriented Programming Using C++(OOP)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZqURF0ZiwQokUxVoGmi7CmGdHlI5pPU0qKV");

                } else if (SelectedSubject.equals("Data structure using C Language (DSU)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZYURF0Zq8LAWmMy5iJeiXV4OhMXR7q6HA97");

                } else if (SelectedSubject.equals("Computer Graphics (CGR)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZ4URF0Z092eq94lUDzHsBVCI4mS7zJriOg7");

                } else if (SelectedSubject.equals("Database Management System (DBMS)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZ1URF0ZAUgJakKJRWH3UhtFPBckUVPs3uo7");

                } else if (SelectedSubject.equals("Digital Techniques (DTE)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZwURF0ZuIxHFsAq6LhqVVrVhiYgPBPKUU9X");

                }


                //Fourth Sem
                else if (SelectedSubject.equals("Java Programming (JPR)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZmIRF0ZVhG4IBpD87RDr4K678N8fbfHpkRX");

                } else if (SelectedSubject.equals("Software Engineering (SEN)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZMIRF0Z4dYuIHM2V9jECN8Ml0SrRhkjVgjV");

                } else if (SelectedSubject.equals("Data Communication and Computer Network (DCC)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZoURF0ZYBMmlR67W7XxmIYGM9XKHHof7D6k");

                } else if (SelectedSubject.equals("Microprocessors (MIC)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZWIRF0ZCaWBFGLTWS4Ht8UwJluO64rUhUSk");

                } else if (SelectedSubject.equals("Gui Application Development Using Vb.Net (GAD)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZVIRF0Z1GI9NfbLw3h7tzXnoAtfHhtlTtkk");

                }




                //Fifth Sem
                else if (SelectedSubject.equals("Advanced Java Programming (AJP)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZLARF0Z9lqBQP7fOTLlyMtSsnsRLB7bNGPy");

                } else if (SelectedSubject.equals("Software Testing (STE)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZDARF0Z0VRn57yc4uzmoMiSQOh980YeGBT7");

                } else if (SelectedSubject.equals("Advanced Computer Network (ACN)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZaIRF0ZKYDzGEj2wYJpStOIfzda6YHkRQJk");

                } else if (SelectedSubject.equals("Operating System (OSY)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZfARF0ZElKbuayv78VACoO9HMG2gbnFdbLV");

                } else if (SelectedSubject.equals("Environmental Studies (EST)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZSARF0ZvxJf23V8gQjNlwN04afDxHrGzgT7");

                }
//                        //Sixth Sem
                else if(SelectedSubject.equals( "Mobile Application Development (MAD)")) {

                    startWebView("https://u.pcloud.link/publink/show?code=kZ7NRF0Z3lJECxn2X6XEPHRwxrvw5fQGka2y");
                }
                else if (SelectedSubject.equals( "Emerging Trends in Computer Engineering (ETI)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZCARF0ZO9ySEfwiSNk11w3qr2Oa6FcNyGby");

                }
                else if (SelectedSubject.equals("Programming with Python (PWP)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZaARF0Z1xouohGJFopBpn8J5l9C5yBJbstV");

                }
                else if (SelectedSubject.equals( "Management (MGT)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZJNRF0ZkULT2zM7fafHlSxhgkaGJuUlYGkk");

                }
                else if (SelectedSubject.equals("Network Information Security (NIS)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZvARF0ZA0VLx4vqrm7medlemkR4sp9bSQiX");

                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        return root;
    }



    ArrayAdapter<CharSequence> setCustomAdapter(int array){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(),
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
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams)
            {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;

                Intent intent = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    intent = fileChooserParams.createIntent();
                }
                try
                {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e)
                {
                    uploadMessage = null;
                    Toast.makeText(getActivity(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
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

//        webView.setDownloadListener(new DownloadListener() {
//            @Override
//            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
//                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//
//                request.setMimeType(mimeType);
//                //------------------------COOKIE!!------------------------
//                String cookies = CookieManager.getInstance().getCookie(url);
//                request.addRequestHeader("cookie", cookies);
//                //------------------------COOKIE!!------------------------
//                request.addRequestHeader("User-Agent", userAgent);
//                request.setDescription("Downloading file...");
//                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
//                request.allowScanningByMediaScanner();
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimeType));
//                DownloadManager dm = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
//                dm.enqueue(request);
//                Toast.makeText(getActivity(), "Downloading File", Toast.LENGTH_LONG).show();
//            }
//        });

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                String fileNameWithExtension = getFileNameWithExtension(contentDisposition);
                if (fileNameWithExtension != null) {
                    int dotIndex = fileNameWithExtension.lastIndexOf(".");
                    if (dotIndex != -1) {
                        String extension = fileNameWithExtension.substring(dotIndex);
                        switch (extension.toLowerCase()) {
                            case ".pdf":
                                mimetype = "application/pdf";
                                break;
                            case ".doc":
                            case ".docx":
                                mimetype = "application/msword";
                                break;
                            // Add more cases for other file types as needed
                            default:
                                mimetype = URLConnection.guessContentTypeFromName(fileNameWithExtension);
                        }
                    }
                }

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setTitle(fileNameWithExtension != null ? fileNameWithExtension : URLUtil.guessFileName(url, contentDisposition, mimetype));
                request.setDescription("Downloading file...");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setMimeType(mimetype);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Environment.DIRECTORY_DOWNLOADS + "/" + fileNameWithExtension);
                DownloadManager dm = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getActivity(), "Downloading...", Toast.LENGTH_SHORT).show();
                getActivity().registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            }
        });



    }
    public  void openDrive(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
    private String getFileNameWithExtension(String contentDisposition) {
        if (contentDisposition != null) {
            int index = contentDisposition.lastIndexOf("/");
            if (index != -1) {
                return contentDisposition.substring(index + 1);
            }
        }
        return null;
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
            Toast.makeText(getActivity(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }
    private BroadcastReceiver onComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            Toast.makeText(getActivity(), "Download completed", Toast.LENGTH_SHORT).show();
            getActivity().unregisterReceiver(this);
        }
    };
}