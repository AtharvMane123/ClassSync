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

                //First sem
//                if(SelectedSubject.equals("ICT Fundamental of ICT.")) {
//                    webView.loadUrl("https://mega.nz/filerequest/i9lUwsdh06I");
//                }
//                else if (SelectedSubject.equals("ENG English.")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/8jlXDCrB#U2lAo7ZUE4Bix8EJ-Dj82w");
//
//                } else if (SelectedSubject.equals("BSC Basic Science (Chemistry).")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/oy8jFDgZ#ae0wwFDyU3mdxQ3bRIbGoQ");
//
//                } else if (SelectedSubject.equals("BMS Basic Mathematics.")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/ontyXR6J#NtiCZp7ZKhHKVjIwbnsCJg");
//
//                }
//
//
//                //second sem
//                else if (SelectedSubject.equals( "Applied Mathematics (AMS)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/cjlRzQZI#CRWXIAVOQ7AKcK5M6LU04Q");
//
//                } else if (SelectedSubject.equals("Basic Electrical and Electronics Engineering (BEE)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/Bvt3BbpK#BYXHiBQb09jxNA14F7-LCQ");
//
//                } else if (SelectedSubject.equals("Programming in C (PIC)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/Q3lD2axQ#iZPR0cBoZju6X4xJpuveZQ");
//
//                } else if (SelectedSubject.equals("Web Page Designing (WPD)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/g71kVCyI#V5Rs1tBSQvmiiL5qonQo1g");
//
//                }
//
//
//                //Third Sem
//                else if (SelectedSubject.equals("Object Oriented Programming Using C++(OOP)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/QzVEFBQb#AGtW377uMU9GOAoA0wW3Hg");
//
//                } else if (SelectedSubject.equals("Data structure using C Language (DSU)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/s71GFZwJ#0baXjAKKqBzBI9QSUBOVMQ");
//
//                } else if (SelectedSubject.equals("Computer Graphics (CGR)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/Bj8kiQgY#mMOdsSCNTpl5ybhosRxY5g");
//
//                } else if (SelectedSubject.equals("Database Management System (DBMS)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/5zdS1TRK#rajXhXnZ5lnxvPFh1oMDpQ");
//
//                } else if (SelectedSubject.equals("Digital Techniques (DTE)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/Aml3jZQA#d9FgKKKiYibsVADnBXm9Kg");
//
//                }
//
//
//                //Fourth Sem
//                else if (SelectedSubject.equals("Java Programming (JPR)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/x2E0ALSI#5KZ_cNEwsEVncqIE79d5EA");
//
//                } else if (SelectedSubject.equals("Software Engineering (SEN)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/l70RBSCR#a-y2mRbeShRvQSjDnB47nA");
//
//                } else if (SelectedSubject.equals("Data Communication and Computer Network (DCC)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/JikgkahA#fe54n16z1rQSEdbEMdwGKw");
//
//                } else if (SelectedSubject.equals("Microprocessors (MIC)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/En8SiIoS#zfBSOzTDOmT2vEgT5SlJjg");
//
//                } else if (SelectedSubject.equals("Gui Application Development Using Vb.Net (GAD)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/Ji8XwTaL#fzortiRm8bqx-2KcEbOOFw");
//
//                }
//
//
//
//
//                //Fifth Sem
//                else if (SelectedSubject.equals("Advanced Java Programming (AJP)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/A21EzDrR#nsNgYyeVNMVCQ7XMW0_PhQ");
//
//                } else if (SelectedSubject.equals("Software Testing (STE)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/83U1HYSZ#gFXUT-FyLPtJUi-7sIjhTg");
//
//                } else if (SelectedSubject.equals("Advanced Computer Network (ACN)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/h3ViSTaa#qgNsALMjky5rJdh94XT9IA");
//
//                } else if (SelectedSubject.equals("Operating System (OSY)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/1jE1VBLJ#xrOVviff0VDLz12tO6Tq1g");
//
//                } else if (SelectedSubject.equals("Environmental Studies (EST)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/QusQHKiD#N_xmzK5HTjynoV9H-qmNNw");
//
//                }
//
//
////                        //Sixth Sem
//               else if(SelectedSubject.equals( "Mobile Application Development (MAD)")) {
//
//
//
//                    startWebView("https://mega.nz/filerequest/i9lUwsdh06I");
//                }
//                else if (SelectedSubject.equals( "Emerging Trends in Computer Engineering (ETI)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/NqFDjQCa#nH7qyc-JPQkaLMGbaCrUbg");
//
//                }
                 if (SelectedSubject.equals("Programming with Python (PWP)")) {
                    startWebView("https://mega.nz/folder/Q2khUI7C#3KVoOWeSEND2imR8VOHSyA");

                }
//                else if (SelectedSubject.equals( "Management (MGT)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/t7FSgJIL#k8KKZ_UdUUx_tOQCXKhPlw");
//
//                }
//                else if (SelectedSubject.equals("Network Information Security (NIS)")) {
//                    OpenMegaCloudStorage("https://mega.nz/folder/YyNA0AJZ#cqytlavZOWrol98gq5CWcg");
//
//                }

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
