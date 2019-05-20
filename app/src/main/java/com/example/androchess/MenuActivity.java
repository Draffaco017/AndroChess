package com.example.androchess;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    Button playButton;
    Button makeTeamButton;
    Button backToLogin;
    TextView userInfo;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("user");

        setContentView(R.layout.activity_menu);
        userInfo=findViewById(R.id.userInfo);
        /*userInfo.setText("Player : "+user.getName()+"\nNumber of wins : "+user.getNbrGamesWon()
                        +"\nNumber of games played : "+user.getNbrGamesPlayed()
                        +"\nRatio of games won : "+user.getRatioGameWon());*/
        userInfo.setText("Player : "+user.getName()+"\nNumber of wins : "+user.getNbrGamesWon()
                    +"\nNumber of games played : "+user.getNbrGamesPlayed()
                    +"\nRatio of games won : "+user.getRatioGameWon()+"%"
                    +"\nUnit 1 : "+user.getUnityName(0)
                    +"\nUnit 2 : "+user.getUnityName(1)
                    +"\nUnit 3 : "+user.getUnityName(2)
                    +"\nUnit 4 : "+user.getUnityName(3)
                    +"\nUnit 5 : "+user.getUnityName(4)
                    +"\nUnit 6 : "+user.getUnityName(5)
                    +"\nUnit 7 : "+user.getUnityName(6)
                    +"\nUnit 8 : "+user.getUnityName(7)
                    +"\nUnit 9 : "+user.getUnityName(8));
        playButton = (Button) findViewById(R.id.btn_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(MenuActivity.this, ChessActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);*/
                Intent intent = new Intent(MenuActivity.this, SelectPlayerTwo.class);
                intent.putExtra("user1", user);
                startActivity(intent);
            }
        });
        //TODO faire le setOnClickListener pour passer au jeu
        makeTeamButton = (Button) findViewById(R.id.btn_makeTeam);
        makeTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,MakeTeamActivityv2.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        backToLogin = findViewById(R.id.btn_backLogin);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
