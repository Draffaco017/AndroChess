package com.example.androchess;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SelectPlayerTwo extends AppCompatActivity {
    private TextView player1;
    private ListView listViewPlayer2;
    private EditText login;
    private EditText password;
    private Button confirm;
    private User user1;
    private User user2;
    private UserDB userDB;
    private ArrayAdapter<String> arrayAdapterListPlayer2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player_two);
        userDB=new UserDB(this);
        user1 = getIntent().getParcelableExtra("user1");
        player1=findViewById(R.id.textUser1);
        player1.setText(user1.getName());
        login=findViewById(R.id.login);
        password=findViewById(R.id.password);
        confirm=findViewById(R.id.confirm);
        listViewPlayer2=findViewById(R.id.Player2List);
        arrayAdapterListPlayer2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, userDB.getAllPlayerNames());
        listViewPlayer2.setAdapter(arrayAdapterListPlayer2);
        listViewPlayer2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                login.setText(parent.getItemAtPosition(position).toString());

            }

        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login.getText().toString().isEmpty()||password.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "please insert both name and password", Toast.LENGTH_SHORT).show();
                }
                else if(login.getText().toString().equals(user1.getName())){
                    Toast.makeText(getApplicationContext(), "Both Players have to be diferents! please select another player", Toast.LENGTH_SHORT).show();
                }
                else{
                    user2 = new User(login.getText().toString(), password.getText().toString());
                    if(userDB.checkIfUserMatchs(user2)==true){

                        Intent intent = new Intent(SelectPlayerTwo.this,ChessActivity.class);
                        try{
                            user2=userDB.loadUser(user2.getName());
                            intent.putExtra("user1", user1);
                            intent.putExtra("user2", user2);
                            Toast.makeText(getApplicationContext(), "Game is about to Start !", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Wrong password !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
