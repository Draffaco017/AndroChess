package com.example.androchess;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class ListFragment extends android.app.ListFragment {
    static User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //final String[] items = {"Alpha", "beta", "gamma"};//getResources().getStringArray(R.array.Planets);
        final String[] items = getResources().getStringArray(R.array.Planets);
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, items);

        setListAdapter(aa);
    }

    public static Fragment newInstance(User oUser) {
        ListFragment mFrgment = new ListFragment();
        user = oUser;
        return mFrgment;
    }
}
