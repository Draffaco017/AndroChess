package com.example.androchess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MakeTeamActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("user");
        addDynamicFragment();
        setContentView(R.layout.activity_make_team);
    }

    private void addDynamicFragment() {
        // TODO Auto-generated method stub
        // creating instance of the HelloWorldFragment.
        android.app.Fragment fg = ListFragment.newInstance(user);
        // adding fragment to relative layout by using layout id
        getFragmentManager().beginTransaction().add(R.id.list1, fg).commit();

        android.app.Fragment fc = ReferenceFragment.newInstance();
        // adding fragment to relative layout by using layout id
        getFragmentManager().beginTransaction().add(R.id.list2, fc).commit();
    }
}
