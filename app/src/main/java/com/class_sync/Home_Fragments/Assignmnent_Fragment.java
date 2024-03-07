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
import android.webkit.CookieManager;
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

public class Assignmnent_Fragment extends Fragment {

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
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_assignmnent_, container, false);
        sem = root.findViewById(R.id.ViewAssignments_Spinner_Class);
        sub = root.findViewById(R.id.ViewAssignmnets_Spinner_subject);
        webView = root.findViewById(R.id.ViewAssignments_webView);
        progressBar = root.findViewById(R.id.ViewAssignments_progressBar);
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
                if(SelectedSubject.equals("Engineering graphics")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZhWzF0ZYcQIhvi9C3JHgXaIQUmjx5NMh3Nk");
                }
                else if (SelectedSubject.equals("ENG English.")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZPWzF0ZNAEkw31nPU48dYJsroPNBymYP7Vy");

                } else if (SelectedSubject.equals("BSC Basic Science (Physics) and (Chemistry).")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZx2zF0ZMtmjd2i1xdQJIRd9MzE0umi0yxny");

                } else if (SelectedSubject.equals("BMS Basic Mathematics.")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZNfJF0ZpUgWmPfeyNHrU7TQx2DU1zTnfjOV");

                }


                //second sem
                else if (SelectedSubject.equals( "Applied Mathematics (AMS)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZEszF0ZGFxzuitzKwyO8D3EmkqNYHrtMQhy");

                } else if (SelectedSubject.equals("Basic Electrical and Electronics Engineering (BEE)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZBDzF0ZLgRVOpVydVBiDG072BAEJVxkcbEk");

                } else if (SelectedSubject.equals("Programming in C (PIC)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZNDzF0ZnasfnfE7Hf5Judgr1f27tREzVT3y");

                } else if (SelectedSubject.equals("Web Page Designing (WPD)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZvDzF0ZBxW0tXcgUTziKJKT8c2GQhLdqW7k");

                }
                else if (SelectedSubject.equals("Linux Basics")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZgDzF0Z1dpvm4QT195Q0Uhu8RdkwkJi8A4k");
                }


                //Third Sem
                else if (SelectedSubject.equals("Object Oriented Programming Using C++(OOP)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZpezF0ZCinThbU27nboDjeqI8AUOm4nM8Pk");

                } else if (SelectedSubject.equals("Data structure using C Language (DSU)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZC1zF0ZT9nPOvV6TWfOxUaQJAxuHLXH1Ql7");

                } else if (SelectedSubject.equals("Computer Graphics (CGR)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZn1zF0ZPBUmABTEOp0Eff9iNnUwoQOUQdUy");

                } else if (SelectedSubject.equals("Database Management System (DBMS)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZO1zF0ZiqRpkYKacS71QNasOi1WHzDm5SW7");

                } else if (SelectedSubject.equals("Digital Techniques (DTE)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZXezF0Z1SIQWOQ4GQSBj65jI8BwHJebzlTX");

                }


                //Fourth Sem
                else if (SelectedSubject.equals("Java Programming (JPR)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZH9zF0ZnOmEfWGNj3HzinB2RI6i8yoVMqA7  ");

                } else if (SelectedSubject.equals("Software Engineering (SEN)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZS9zF0ZbRdxBlJr3kbJMcgED0noUXYgGOkX");

                } else if (SelectedSubject.equals("Data Communication and Computer Network (DCC)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZznzF0ZeBpIfggGKTFiJ5lgs7pCYQGbl2zV");

                } else if (SelectedSubject.equals("Microprocessors (MIC)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZQ9zF0Z9MDsdx3iw4RFwkwACrniqhuHltPV");

                } else if (SelectedSubject.equals("Gui Application Development Using Vb.Net (GAD)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZ4nzF0ZXPEPen0FxgSp53CfBa01z8XR3tQy");

                }




                //Fifth Sem
                else if (SelectedSubject.equals("Advanced Java Programming (AJP)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZXMzF0ZXaVvjyuUBRmIxPhE0EIyES0CwI6V");

                } else if (SelectedSubject.equals("Software Testing (STE)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZwMzF0ZTBxVjlDP8SuvfVuLBrYQYQoOWcD7");

                } else if (SelectedSubject.equals("Advanced Computer Network (ACN)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZ7MzF0ZSoi5XRhrsBuwJLEvPPcWBYlYAGQX");

                } else if (SelectedSubject.equals("Operating System (OSY)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZuMzF0ZDpkYtdzXVrSoCoSkTfdfijefFSv7");

                } else if (SelectedSubject.equals("Environmental Studies (EST)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZFMzF0Z1p0QROyoyHpRo6lsnhJBUY4x6lxk");

                }
//                        //Sixth Sem
               else if(SelectedSubject.equals( "Mobile Application Development (MAD)")) {

                    startWebView("https://u.pcloud.link/publink/show?code=kZATzF0ZIjlJBlHtzARUv2tzmklJguKLD3w7");
                }
                else if (SelectedSubject.equals( "Emerging Trends in Computer Engineering (ETI)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZKTzF0ZSLVPqACfSX51fhtg8mBCdkyFbv3X");

                } 
                else if (SelectedSubject.equals("Programming with Python (PWP)")) {
                   startWebView("https://u.pcloud.link/publink/show?code=kZVgzF0Z9G6mVfrSFPYwi5xXucU0w0LBCFKX");

                }
                else if (SelectedSubject.equals( "Management (MGT)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZVgzF0Z9G6mVfrSFPYwi5xXucU0w0LBCFKX");

                }
                else if (SelectedSubject.equals("Network Information Security (NIS)")) {
                    startWebView("https://u.pcloud.link/publink/show?code=kZcTzF0ZtuMORc58SUXXJE3cNnBYb4TV75jk");

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
