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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListFragment extends android.app.ListFragment {
    private static User user;
    private ArrayList<String> items;
    private ArrayAdapter<String> aa;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //final String[] items = {"Alpha", "beta", "gamma"};//getResources().getStringArray(R.array.Planets);
        ArrayList<String> items = user.getUnitiesNames();//getResources().getStringArray(R.array.Planets);
        aa = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, items);
        setListAdapter(aa);
    }

    public static Fragment newInstance(User oUser) {
        ListFragment mFrgment = new ListFragment();
        user = oUser;
        return mFrgment;
    }
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) { // la magie d'android studio
        super.onListItemClick(l, v, pos, id);
        Toast.makeText(getActivity(), getListView().getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
        items.remove(getListView().getItemAtPosition(pos));
        aa.notifyDataSetChanged();
    }

}
