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
                if(SelectedSubject.equals("Engineering graphics")) {
//                    Toast.makeText(UploadAssignments.this, "For now this category is not been created", Toast.LENGTH_SHORT).show();
                                        startWebView("https://u.pcloud.com/#page=puplink&code=fQvkZA4e8gBCD4KuQ5YMDpd3BPzmRRtlX");
                }
                else if (SelectedSubject.equals("ENG English.")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=DQvkZA4Njx7bVBG51vucJvGYqWRlBFlsy");

                } else if (SelectedSubject.equals("BSC Basic Science (Physics) and (Chemistry).")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=PQvkZBwihEyzJPbb3g044z4slbuSP80hy");

                } else if (SelectedSubject.equals("BMS Basic Mathematics.")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=PQvkZBwihEyzJPbb3g044z4slbuSP80hy");

                }


                //second sem
                else if (SelectedSubject.equals( "Applied Mathematics (AMS)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=MQvkZLFjKPODTVghPAsbJf9koBR51d4PX");

                } else if (SelectedSubject.equals("Basic Electrical and Electronics Engineering (BEE)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=gQvkZ4JoXiJwD97yeCiMcTPHn0J4U2KV0");

                } else if (SelectedSubject.equals("Programming in C (PIC)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=wQvkZRPpMIbRFE9zvrr3veuWJbyiVNney");

                } else if (SelectedSubject.equals("Web Page Designing (WPD)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=lQvkZbfjo1HOblfmeO8FRQVFjpSsVGXS7");

                } else if (SelectedSubject.equals("Linux Basics")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=PQvkZBwihEyzJPbb3g044z4slbuSP80hy");
                }


                //Third Sem
                else if (SelectedSubject.equals("Object Oriented Programming Using C++(OOP)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=IQvkZBWkqOo4hK4uqoYelj0zbP5BtNRnV");

                } else if (SelectedSubject.equals("Data structure using C Language (DSU)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=tQvkZB4PgBPwqg4bbUnmObymjkFIpgdQk");

                } else if (SelectedSubject.equals("Computer Graphics (CGR)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=qQvkZi60yNSNKPGLrh7bO0dXsdYLNfjq7");

                } else if (SelectedSubject.equals("Database Management System (DBMS)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=OQvkZ9Lu65Hm2Fo7yCaOjYnXqp7UG9r7k");

                } else if (SelectedSubject.equals("Digital Techniques (DTE)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=UQvkZLUe75JN4YnRthCvkXdoBbzabo72V");
                }


                //Fourth Sem
                else if (SelectedSubject.equals("Java Programming (JPR)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=cQvkZwt5gaCFIfMk6zWWY0ILPlmQdbnPy");

                } else if (SelectedSubject.equals("Software Engineering (SEN)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=7YvkZ2VoDUl7Ybnudv3rFFLPxomhxAYqk");

                } else if (SelectedSubject.equals("Data Communication and Computer Network (DCC)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=yYvkZYSg1siDXLeSt0FE6ybbcXphswq5X");

                } else if (SelectedSubject.equals("Microprocessors (MIC)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=yYvkZYSg1siDXLeSt0FE6ybbcXphswq5X");

                } else if (SelectedSubject.equals("Gui Application Development Using Vb.Net (GAD)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=aQvkZlrBGGrEXwsbgDiWQU30bEYHLMqBX");

                }


                //Fifth Sem
                else if (SelectedSubject.equals("Advanced Java Programming (AJP)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=VYvkZWIoVpmlQJiHaSJsJmdBpJ80sDQxk");

                } else if (SelectedSubject.equals("Software Testing (STE)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=FYvkZdqD3kS5pjqmkncKpYpUtAyWDDbA7");

                } else if (SelectedSubject.equals("Advanced Computer Network (ACN)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=XYvkZj9s6cTMistf7qN5yTmmUB7PWaUO7");

                } else if (SelectedSubject.equals("Operating System (OSY)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=5YvkZ0JLXzxN7gyViG2GwF6S48pjvUqpV");

                } else if (SelectedSubject.equals("Environmental Studies (EST)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=0YvkZydk2N62GA0HYNxTMS3h5qJK7qkyV");
                }
//                       //Sixth Sem
                else if (SelectedSubject.equals( "Mobile Application Development (MAD)")) {
                     startWebView("https://u.pcloud.com/#page=puplink&code=LYvkZ21ytrAxui0SzFF3bjdj9r8UzUf47");
                }
                else if (SelectedSubject.equals( "Emerging Trends in Computer Engineering (ETI)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=HYvkZy2Gl2X0b4lLdhm569OQqBHHD7cLV");

                }
                else if (SelectedSubject.equals("Programming with Python (PWP)")) {
                        startWebView("https://u.pcloud.com/#page=puplink&code=8YvkZ9v6hjJCpmWXJB9HVdVpEd79n8L5y");
                }
                else if (SelectedSubject.equals( "Management (MGT)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=RYvkZ1lWQXi2sxI7HPq6JQfgTHfAYfY6X");

                }
                else if (SelectedSubject.equals("Network Information Security (NIS)")) {
                    startWebView("https://u.pcloud.com/#page=puplink&code=4YvkZOslgLNnfDEQfhDMiv1boG7pieo1y");

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

    public  void openDrive(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
