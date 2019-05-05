package com.example.androchess;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addDynamicFragment();
    }
    private void addDynamicFragment() {
        // TODO Auto-generated method stub
        // creating instance of the HelloWorldFragment.
        android.app.Fragment fg = LoginFragment.newInstance();
        // adding fragment to relative layout by using layout id
        getFragmentManager().beginTransaction().add(R.id.fragment, fg).commit();
    }
}
