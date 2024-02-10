package com.class_sync.Home_Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.class_sync.HomeFragment;
import com.class_sync.R;

import java.net.URI;
import java.util.Objects;


public class MsbteResources_Fragement extends Fragment {

CardView QuestionPapers,ModelAnswers,SamplePapers;
Spinner sem,sub;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_msbte_resources__fragement, container, false);;
//     QuestionPapers=root.findViewById(R.id.QuestionPapers_cardview);
        sem = root.findViewById(R.id.Spinner_Sem);
        sub = root.findViewById(R.id.Spinner_subject);

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
                    if(SelectedSubject.equals("ICT Fundamental of ICT.")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/YyNA0AJZ#cqytlavZOWrol98gq5CWcg");
                    } else if (SelectedSubject.equals("ENG English.")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/8jlXDCrB#U2lAo7ZUE4Bix8EJ-Dj82w");

                    } else if (SelectedSubject.equals("BSC Basic Science (Chemistry).")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/oy8jFDgZ#ae0wwFDyU3mdxQ3bRIbGoQ");

                    } else if (SelectedSubject.equals("BMS Basic Mathematics.")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/ontyXR6J#NtiCZp7ZKhHKVjIwbnsCJg");

                    }


                        //second sem
                    else if (SelectedSubject.equals( "Applied Mathematics (AMS)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/cjlRzQZI#CRWXIAVOQ7AKcK5M6LU04Q");

                    } else if (SelectedSubject.equals("Basic Electrical and Electronics Engineering (BEE)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/Bvt3BbpK#BYXHiBQb09jxNA14F7-LCQ");

                    } else if (SelectedSubject.equals("Programming in C (PIC)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/Q3lD2axQ#iZPR0cBoZju6X4xJpuveZQ");

                    } else if (SelectedSubject.equals("Web Page Designing (WPD)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/g71kVCyI#V5Rs1tBSQvmiiL5qonQo1g");

                    }


                //Third Sem
                    else if (SelectedSubject.equals("Object Oriented Programming Using C++(OOP)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/QzVEFBQb#AGtW377uMU9GOAoA0wW3Hg");

                    } else if (SelectedSubject.equals("Data structure using C Language (DSU)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/s71GFZwJ#0baXjAKKqBzBI9QSUBOVMQ");

                    } else if (SelectedSubject.equals("Computer Graphics (CGR)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/Bj8kiQgY#mMOdsSCNTpl5ybhosRxY5g");

                    } else if (SelectedSubject.equals("Database Management System (DBMS)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/5zdS1TRK#rajXhXnZ5lnxvPFh1oMDpQ");

                    } else if (SelectedSubject.equals("Digital Techniques (DTE)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/Aml3jZQA#d9FgKKKiYibsVADnBXm9Kg");

                    }


                //Fourth Sem
                    else if (SelectedSubject.equals("Java Programming (JPR)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/x2E0ALSI#5KZ_cNEwsEVncqIE79d5EA");

                    } else if (SelectedSubject.equals("Software Engineering (SEN)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/l70RBSCR#a-y2mRbeShRvQSjDnB47nA");

                    } else if (SelectedSubject.equals("Data Communication and Computer Network (DCC)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/JikgkahA#fe54n16z1rQSEdbEMdwGKw");

                    } else if (SelectedSubject.equals("Microprocessors (MIC)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/En8SiIoS#zfBSOzTDOmT2vEgT5SlJjg");

                    } else if (SelectedSubject.equals("Gui Application Development Using Vb.Net (GAD)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/Ji8XwTaL#fzortiRm8bqx-2KcEbOOFw");

                    }




                    //Fifth Sem
                    else if (SelectedSubject.equals("Advanced Java Programming (AJP)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/A21EzDrR#nsNgYyeVNMVCQ7XMW0_PhQ");

                    } else if (SelectedSubject.equals("Software Testing (STE)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/83U1HYSZ#gFXUT-FyLPtJUi-7sIjhTg");

                    } else if (SelectedSubject.equals("Advanced Computer Network (ACN)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/h3ViSTaa#qgNsALMjky5rJdh94XT9IA");

                    } else if (SelectedSubject.equals("Operating System (OSY)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/1jE1VBLJ#xrOVviff0VDLz12tO6Tq1g");

                    } else if (SelectedSubject.equals("Environmental Studies (EST)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/QusQHKiD#N_xmzK5HTjynoV9H-qmNNw");

                    }


//                        //Sixth Sem
                    else if(SelectedSubject.equals( "Mobile Application Development (MAD)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/lzMDUYwZ#PAxTtN7tNnVCXXC0A3Zilw");

                    }
                    else if (SelectedSubject.equals( "Emerging Trends in Computer Engineering (ETI)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/NqFDjQCa#nH7qyc-JPQkaLMGbaCrUbg");

                    }
                    else if (SelectedSubject.equals("Programming with Python (PWP)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/o79FnAKD#1aZL9b-mPHporUxLgOtPQA");

                    }
                    else if (SelectedSubject.equals( "Management (MGT)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/t7FSgJIL#k8KKZ_UdUUx_tOQCXKhPlw");

                    }
                    else if (SelectedSubject.equals("Network Information Security (NIS)")) {
                        OpenMegaCloudStorage("https://mega.nz/folder/YyNA0AJZ#cqytlavZOWrol98gq5CWcg");

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
    public void OpenMegaCloudStorage(String url ){
        Uri  uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }
}