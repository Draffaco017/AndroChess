package com.example.androchess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


import java.util.ArrayList;

public class MakeTeamActivityv2 extends Activity {
    private UserDB userDB;
    private User user;
    private ListView myTeam;
    private ListView choices;
    private ArrayList<String> myTeamList;
    private ArrayAdapter<String> myTeamAdapter;
    private ArrayAdapter<String> choicesAdaptor;
    private Button confirm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_team_v2);
        userDB=new UserDB(getApplicationContext());
        user = getIntent().getParcelableExtra("user");
        myTeam = findViewById(R.id.myteam);
        choices = findViewById(R.id.choices);
        confirm=findViewById(R.id.confirm);
        myTeamList=user.getUnitiesNames();
        myTeamAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myTeamList);
        choicesAdaptor = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.list_examples));
        myTeam.setAdapter(myTeamAdapter);
        choices.setAdapter(choicesAdaptor);
        myTeam.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                           int position, long id) {

                myTeamList.remove(position);

                myTeamAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), "Item Deleted", Toast.LENGTH_LONG).show();

            }

        });
        choices.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if(myTeamList.size()<9){
                    myTeamList.add(parent.getItemAtPosition(position).toString());

                    myTeamAdapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Team is already full", Toast.LENGTH_SHORT).show();
                }

            }

        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myTeamList.size()==9){
                    user.setUnitiesNames(myTeamList);
                    userDB.updateTeamUser(user);
                    user=userDB.loadUser(user.getName());
                    Intent intent = new Intent(MakeTeamActivityv2.this,MenuActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Team is not of 9 unities, please add unities", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
