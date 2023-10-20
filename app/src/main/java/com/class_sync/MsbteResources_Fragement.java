package com.class_sync;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MsbteResources_Fragement extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_msbte_resources__fragement, container, false);;
        return root;
    }
}