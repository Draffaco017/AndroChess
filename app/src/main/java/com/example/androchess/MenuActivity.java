package com.example.androchess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    Button playButton;
    Button makeTeamButton;
    TextView userInfo;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("user");
        setContentView(R.layout.activity_menu);
        playButton = (Button) findViewById(R.id.btn_play);
        makeTeamButton = (Button) findViewById(R.id.btn_makeTeam);
        makeTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,MakeTeamActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

}
