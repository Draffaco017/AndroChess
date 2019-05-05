package com.example.androchess;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class SignUpFragment extends Fragment {
    static String string;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        EditText edit = (EditText) view.findViewById(R.id.input_pseudo);
        edit.setText(string);
        return view;
    }

    public static Fragment newInstance(String texte) {
        SignUpFragment mFrgment = new SignUpFragment();
        string = texte;
        return mFrgment;
    }
}
