package com.example.androchess;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Ref;

public class ReferenceFragment extends ListFragment {
    String[] items;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reference, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //final String[] items = {"Alpha2","beta2", "gamma2"};
        items = getResources().getStringArray(R.array.list_examples);
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, items);

        setListAdapter(aa);
    }

    public static Fragment newInstance() {
        ReferenceFragment mFrgment = new ReferenceFragment();
        return mFrgment;
    }
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) { // la magie d'android studio
        super.onListItemClick(l, v, pos, id);
        Toast.makeText(getActivity(), getListView().getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();

    }
}
