package com.example.androchess;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ChessActivity extends AppCompatActivity implements View.OnClickListener{
    public Boolean FirstPlayerTurn;
    public ArrayList<Coordinates> listOfCoordinates = new ArrayList<>();
    public Position[][] Board = new Position[8][8];
    public Position[][] Board2 = new Position[8][8];
    public Boolean AnythingSelected = false;
    public Coordinates lastPos = null ;
    public Coordinates clickedPosition = new Coordinates(0, 0);
    public TextView game_over;
    public TextView[][] DisplayBoard = new TextView[8][8];
    public TextView[][] DisplayBoardBackground = new TextView[8][8];
    public TextView messageBoard;
    public ArrayList<Position[][]> LastMoves = new ArrayList<>();
    public LinearLayout pawn_choices;
    public int numberOfMoves;
    public boolean isMoved = false;
    public boolean gameStart = false;
    public int step = 0;
    public String messageTexte;

    public TextView message;

    //TODO dégager tout ça, au final on aura un array par team venant de la DB

    Unity base_blue;
    Unity base_red;

    Unity shooter_blue;
    Unity aircraft_blue;
    Unity melle_blue;

    Unity shooter_red;
    Unity aircraft_red;
    Unity melle_red;

    Button button;

    public ArrayList<Unity> blueTeam = new ArrayList<>();
    public ArrayList<Unity> redTeam = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_chess);
        message = (TextView) findViewById(R.id.message);
        initializeBoard();

    }

    private void initializeBoard() {
        //TODO pareil ici ça dégagera !
        //TODO je pense que ce qu'il faut faire, c'est enregistrer les noms dans la DB, et créer les objets ICI afin de donner l'équipe
        base_blue = new Base(true);
        base_red = new Base(false);

        shooter_blue = new Shooter(true);
        aircraft_blue = new Aircraft(true);
        melle_blue = new Melee(true);

        shooter_red = new Shooter(false);
        aircraft_red = new Aircraft(false);
        melle_red = new Melee(false);

        redTeam.add(base_red);
        redTeam.add(aircraft_red);
        redTeam.add(shooter_red);
        redTeam.add(melle_red);

        blueTeam.add(base_blue);
        blueTeam.add(aircraft_blue);
        blueTeam.add(shooter_blue);
        blueTeam.add(melle_blue);



        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Board[i][j] = new Position(null);
                Board2[i][j] = new Position(null);
            }
        }

        message.setText("team blue, place your base");

        /*Board[0][0].setPiece(base_blue);
        Board[1][0].setPiece(shooter_blue);
        Board[2][0].setPiece(aircraft_blue);
        Board[3][0].setPiece(melle_blue);

        Board[0][7].setPiece(base_red);
        Board[1][7].setPiece(shooter_red);
        Board[2][7].setPiece(aircraft_red);
        Board[3][7].setPiece(melle_red);*/


        DisplayBoard[0][0] = (TextView) findViewById(R.id.R00);
        DisplayBoardBackground[0][0] = (TextView) findViewById(R.id.R000);
        DisplayBoard[1][0] = (TextView) findViewById(R.id.R10);
        DisplayBoardBackground[1][0] = (TextView) findViewById(R.id.R010);
        DisplayBoard[2][0] = (TextView) findViewById(R.id.R20);
        DisplayBoardBackground[2][0] = (TextView) findViewById(R.id.R020);
        DisplayBoard[3][0] = (TextView) findViewById(R.id.R30);
        DisplayBoardBackground[3][0] = (TextView) findViewById(R.id.R030);
        DisplayBoard[4][0] = (TextView) findViewById(R.id.R40);
        DisplayBoardBackground[4][0] = (TextView) findViewById(R.id.R040);
        DisplayBoard[5][0] = (TextView) findViewById(R.id.R50);
        DisplayBoardBackground[5][0] = (TextView) findViewById(R.id.R050);
        DisplayBoard[6][0] = (TextView) findViewById(R.id.R60);
        DisplayBoardBackground[6][0] = (TextView) findViewById(R.id.R060);
        DisplayBoard[7][0] = (TextView) findViewById(R.id.R70);
        DisplayBoardBackground[7][0] = (TextView) findViewById(R.id.R070);

        DisplayBoard[0][1] = (TextView) findViewById(R.id.R01);
        DisplayBoardBackground[0][1] = (TextView) findViewById(R.id.R001);
        DisplayBoard[1][1] = (TextView) findViewById(R.id.R11);
        DisplayBoardBackground[1][1] = (TextView) findViewById(R.id.R011);
        DisplayBoard[2][1] = (TextView) findViewById(R.id.R21);
        DisplayBoardBackground[2][1] = (TextView) findViewById(R.id.R021);
        DisplayBoard[3][1] = (TextView) findViewById(R.id.R31);
        DisplayBoardBackground[3][1] = (TextView) findViewById(R.id.R031);
        DisplayBoard[4][1] = (TextView) findViewById(R.id.R41);
        DisplayBoardBackground[4][1] = (TextView) findViewById(R.id.R041);
        DisplayBoard[5][1] = (TextView) findViewById(R.id.R51);
        DisplayBoardBackground[5][1] = (TextView) findViewById(R.id.R051);
        DisplayBoard[6][1] = (TextView) findViewById(R.id.R61);
        DisplayBoardBackground[6][1] = (TextView) findViewById(R.id.R061);
        DisplayBoard[7][1] = (TextView) findViewById(R.id.R71);
        DisplayBoardBackground[7][1] = (TextView) findViewById(R.id.R071);

        DisplayBoard[0][2] = (TextView) findViewById(R.id.R02);
        DisplayBoardBackground[0][2] = (TextView) findViewById(R.id.R002);
        DisplayBoard[1][2] = (TextView) findViewById(R.id.R12);
        DisplayBoardBackground[1][2] = (TextView) findViewById(R.id.R012);
        DisplayBoard[2][2] = (TextView) findViewById(R.id.R22);
        DisplayBoardBackground[2][2] = (TextView) findViewById(R.id.R022);
        DisplayBoard[3][2] = (TextView) findViewById(R.id.R32);
        DisplayBoardBackground[3][2] = (TextView) findViewById(R.id.R032);
        DisplayBoard[4][2] = (TextView) findViewById(R.id.R42);
        DisplayBoardBackground[4][2] = (TextView) findViewById(R.id.R042);
        DisplayBoard[5][2] = (TextView) findViewById(R.id.R52);
        DisplayBoardBackground[5][2] = (TextView) findViewById(R.id.R052);
        DisplayBoard[6][2] = (TextView) findViewById(R.id.R62);
        DisplayBoardBackground[6][2] = (TextView) findViewById(R.id.R062);
        DisplayBoard[7][2] = (TextView) findViewById(R.id.R72);
        DisplayBoardBackground[7][2] = (TextView) findViewById(R.id.R072);

        DisplayBoard[0][3] = (TextView) findViewById(R.id.R03);
        DisplayBoardBackground[0][3] = (TextView) findViewById(R.id.R003);
        DisplayBoard[1][3] = (TextView) findViewById(R.id.R13);
        DisplayBoardBackground[1][3] = (TextView) findViewById(R.id.R013);
        DisplayBoard[2][3] = (TextView) findViewById(R.id.R23);
        DisplayBoardBackground[2][3] = (TextView) findViewById(R.id.R023);
        DisplayBoard[3][3] = (TextView) findViewById(R.id.R33);
        DisplayBoardBackground[3][3] = (TextView) findViewById(R.id.R033);
        DisplayBoard[4][3] = (TextView) findViewById(R.id.R43);
        DisplayBoardBackground[4][3] = (TextView) findViewById(R.id.R043);
        DisplayBoard[5][3] = (TextView) findViewById(R.id.R53);
        DisplayBoardBackground[5][3] = (TextView) findViewById(R.id.R053);
        DisplayBoard[6][3] = (TextView) findViewById(R.id.R63);
        DisplayBoardBackground[6][3] = (TextView) findViewById(R.id.R063);
        DisplayBoard[7][3] = (TextView) findViewById(R.id.R73);
        DisplayBoardBackground[7][3] = (TextView) findViewById(R.id.R073);

        DisplayBoard[0][4] = (TextView) findViewById(R.id.R04);
        DisplayBoardBackground[0][4] = (TextView) findViewById(R.id.R004);
        DisplayBoard[1][4] = (TextView) findViewById(R.id.R14);
        DisplayBoardBackground[1][4] = (TextView) findViewById(R.id.R014);
        DisplayBoard[2][4] = (TextView) findViewById(R.id.R24);
        DisplayBoardBackground[2][4] = (TextView) findViewById(R.id.R024);
        DisplayBoard[3][4] = (TextView) findViewById(R.id.R34);
        DisplayBoardBackground[3][4] = (TextView) findViewById(R.id.R034);
        DisplayBoard[4][4] = (TextView) findViewById(R.id.R44);
        DisplayBoardBackground[4][4] = (TextView) findViewById(R.id.R044);
        DisplayBoard[5][4] = (TextView) findViewById(R.id.R54);
        DisplayBoardBackground[5][4] = (TextView) findViewById(R.id.R054);
        DisplayBoard[6][4] = (TextView) findViewById(R.id.R64);
        DisplayBoardBackground[6][4] = (TextView) findViewById(R.id.R064);
        DisplayBoard[7][4] = (TextView) findViewById(R.id.R74);
        DisplayBoardBackground[7][4] = (TextView) findViewById(R.id.R074);

        DisplayBoard[0][5] = (TextView) findViewById(R.id.R05);
        DisplayBoardBackground[0][5] = (TextView) findViewById(R.id.R005);
        DisplayBoard[1][5] = (TextView) findViewById(R.id.R15);
        DisplayBoardBackground[1][5] = (TextView) findViewById(R.id.R015);
        DisplayBoard[2][5] = (TextView) findViewById(R.id.R25);
        DisplayBoardBackground[2][5] = (TextView) findViewById(R.id.R025);
        DisplayBoard[3][5] = (TextView) findViewById(R.id.R35);
        DisplayBoardBackground[3][5] = (TextView) findViewById(R.id.R035);
        DisplayBoard[4][5] = (TextView) findViewById(R.id.R45);
        DisplayBoardBackground[4][5] = (TextView) findViewById(R.id.R045);
        DisplayBoard[5][5] = (TextView) findViewById(R.id.R55);
        DisplayBoardBackground[5][5] = (TextView) findViewById(R.id.R055);
        DisplayBoard[6][5] = (TextView) findViewById(R.id.R65);
        DisplayBoardBackground[6][5] = (TextView) findViewById(R.id.R065);
        DisplayBoard[7][5] = (TextView) findViewById(R.id.R75);
        DisplayBoardBackground[7][5] = (TextView) findViewById(R.id.R075);

        DisplayBoard[0][6] = (TextView) findViewById(R.id.R06);
        DisplayBoardBackground[0][6] = (TextView) findViewById(R.id.R006);
        DisplayBoard[1][6] = (TextView) findViewById(R.id.R16);
        DisplayBoardBackground[1][6] = (TextView) findViewById(R.id.R016);
        DisplayBoard[2][6] = (TextView) findViewById(R.id.R26);
        DisplayBoardBackground[2][6] = (TextView) findViewById(R.id.R026);
        DisplayBoard[3][6] = (TextView) findViewById(R.id.R36);
        DisplayBoardBackground[3][6] = (TextView) findViewById(R.id.R036);
        DisplayBoard[4][6] = (TextView) findViewById(R.id.R46);
        DisplayBoardBackground[4][6] = (TextView) findViewById(R.id.R046);
        DisplayBoard[5][6] = (TextView) findViewById(R.id.R56);
        DisplayBoardBackground[5][6] = (TextView) findViewById(R.id.R056);
        DisplayBoard[6][6] = (TextView) findViewById(R.id.R66);
        DisplayBoardBackground[6][6] = (TextView) findViewById(R.id.R066);
        DisplayBoard[7][6] = (TextView) findViewById(R.id.R76);
        DisplayBoardBackground[7][6] = (TextView) findViewById(R.id.R076);

        DisplayBoard[0][7] = (TextView) findViewById(R.id.R07);
        DisplayBoardBackground[0][7] = (TextView) findViewById(R.id.R007);
        DisplayBoard[1][7] = (TextView) findViewById(R.id.R17);
        DisplayBoardBackground[1][7] = (TextView) findViewById(R.id.R017);
        DisplayBoard[2][7] = (TextView) findViewById(R.id.R27);
        DisplayBoardBackground[2][7] = (TextView) findViewById(R.id.R027);
        DisplayBoard[3][7] = (TextView) findViewById(R.id.R37);
        DisplayBoardBackground[3][7] = (TextView) findViewById(R.id.R037);
        DisplayBoard[4][7] = (TextView) findViewById(R.id.R47);
        DisplayBoardBackground[4][7] = (TextView) findViewById(R.id.R047);
        DisplayBoard[5][7] = (TextView) findViewById(R.id.R57);
        DisplayBoardBackground[5][7] = (TextView) findViewById(R.id.R057);
        DisplayBoard[6][7] = (TextView) findViewById(R.id.R67);
        DisplayBoardBackground[6][7] = (TextView) findViewById(R.id.R067);
        DisplayBoard[7][7] = (TextView) findViewById(R.id.R77);
        DisplayBoardBackground[7][7] = (TextView) findViewById(R.id.R077);
        messageBoard = (TextView) findViewById(R.id.R100);
        messageBoard.setBackgroundResource(R.drawable.base_blue);
        for(int g=0;g<8;g++){
            for(int h=0;h<8;h++){
                if(Board[g][h].getUnity()==null){
                    Board2[g][h].setPiece(null);
                }else{
                    Board2[g][h].setPiece(Board[g][h].getUnity());
                }
            }
        }

        numberOfMoves = 0;
        AnythingSelected = false;
        FirstPlayerTurn = true;
        setColorAtAllowedPlace();
        setBoard();
    }

    private void setBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Unity p = Board[i][j].getUnity();
                int x;

                if (Board[i][j].getUnity() != null) {
                    if (p instanceof Base) x = 0;
                    else if (p instanceof Shooter) x = 1;
                    else if (p instanceof Melee) x = 2;
                    else if (p instanceof Aircraft) x = 3;
                    else x = 4;

                    switch (x) {
                        case 0:
                            if(p.isBlue())
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.base_blue);
                            else
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.base_red);
                            break;

                        case 1:
                            if(p.isBlue())
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.tank_blue);
                            else
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.tank_red);
                            break;

                        case 2:
                            if(p.isBlue())
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.melee_blue);
                            else
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.melee_red);
                            break;

                        case 3:
                            if(p.isBlue())
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.aircraft_blue);
                            else
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.aircraft_red);
                            break;

                        default:

                    }
                }else{
                    DisplayBoard[i][j].setBackgroundResource(0);
                }
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.R00:
                clickedPosition = new Coordinates(0, 0);
                break;
            case R.id.R10:
                clickedPosition.setX(1);
                clickedPosition.setY(0);
                break;
            case R.id.R20:
                clickedPosition.setX(2);
                clickedPosition.setY(0);
                break;
            case R.id.R30:
                clickedPosition.setX(3);
                clickedPosition.setY(0);
                break;
            case R.id.R40:
                clickedPosition.setX(4);
                clickedPosition.setY(0);
                break;
            case R.id.R50:
                clickedPosition.setX(5);
                clickedPosition.setY(0);
                break;
            case R.id.R60:
                clickedPosition.setX(6);
                clickedPosition.setY(0);
                break;
            case R.id.R70:
                clickedPosition.setX(7);
                clickedPosition.setY(0);
                break;

            case R.id.R01:
                clickedPosition.setX(0);
                clickedPosition.setY(1);
                break;
            case R.id.R11:
                clickedPosition.setX(1);
                clickedPosition.setY(1);
                break;
            case R.id.R21:
                clickedPosition.setX(2);
                clickedPosition.setY(1);
                break;
            case R.id.R31:
                clickedPosition.setX(3);
                clickedPosition.setY(1);
                break;
            case R.id.R41:
                clickedPosition.setX(4);
                clickedPosition.setY(1);
                break;
            case R.id.R51:
                clickedPosition.setX(5);
                clickedPosition.setY(1);
                break;
            case R.id.R61:
                clickedPosition.setX(6);
                clickedPosition.setY(1);
                break;
            case R.id.R71:
                clickedPosition.setX(7);
                clickedPosition.setY(1);
                break;

            case R.id.R02:
                clickedPosition.setX(0);
                clickedPosition.setY(2);
                break;
            case R.id.R12:
                clickedPosition.setX(1);
                clickedPosition.setY(2);
                break;
            case R.id.R22:
                clickedPosition.setX(2);
                clickedPosition.setY(2);
                break;
            case R.id.R32:
                clickedPosition.setX(3);
                clickedPosition.setY(2);
                break;
            case R.id.R42:
                clickedPosition.setX(4);
                clickedPosition.setY(2);
                break;
            case R.id.R52:
                clickedPosition.setX(5);
                clickedPosition.setY(2);
                break;
            case R.id.R62:
                clickedPosition.setX(6);
                clickedPosition.setY(2);
                break;
            case R.id.R72:
                clickedPosition.setX(7);
                clickedPosition.setY(2);
                break;

            case R.id.R03:
                clickedPosition.setX(0);
                clickedPosition.setY(3);
                break;
            case R.id.R13:
                clickedPosition.setX(1);
                clickedPosition.setY(3);
                break;
            case R.id.R23:
                clickedPosition.setX(2);
                clickedPosition.setY(3);
                break;
            case R.id.R33:
                clickedPosition.setX(3);
                clickedPosition.setY(3);
                break;
            case R.id.R43:
                clickedPosition.setX(4);
                clickedPosition.setY(3);
                break;
            case R.id.R53:
                clickedPosition.setX(5);
                clickedPosition.setY(3);
                break;
            case R.id.R63:
                clickedPosition.setX(6);
                clickedPosition.setY(3);
                break;
            case R.id.R73:
                clickedPosition.setX(7);
                clickedPosition.setY(3);
                break;

            case R.id.R04:
                clickedPosition.setX(0);
                clickedPosition.setY(4);
                break;
            case R.id.R14:
                clickedPosition.setX(1);
                clickedPosition.setY(4);
                break;
            case R.id.R24:
                clickedPosition.setX(2);
                clickedPosition.setY(4);
                break;
            case R.id.R34:
                clickedPosition.setX(3);
                clickedPosition.setY(4);
                break;
            case R.id.R44:
                clickedPosition.setX(4);
                clickedPosition.setY(4);
                break;
            case R.id.R54:
                clickedPosition.setX(5);
                clickedPosition.setY(4);
                break;
            case R.id.R64:
                clickedPosition.setX(6);
                clickedPosition.setY(4);
                break;
            case R.id.R74:
                clickedPosition.setX(7);
                clickedPosition.setY(4);
                break;

            case R.id.R05:
                clickedPosition.setX(0);
                clickedPosition.setY(5);
                break;
            case R.id.R15:
                clickedPosition.setX(1);
                clickedPosition.setY(5);
                break;
            case R.id.R25:
                clickedPosition.setX(2);
                clickedPosition.setY(5);
                break;
            case R.id.R35:
                clickedPosition.setX(3);
                clickedPosition.setY(5);
                break;
            case R.id.R45:
                clickedPosition.setX(4);
                clickedPosition.setY(5);
                break;
            case R.id.R55:
                clickedPosition.setX(5);
                clickedPosition.setY(5);
                break;
            case R.id.R65:
                clickedPosition.setX(6);
                clickedPosition.setY(5);
                break;
            case R.id.R75:
                clickedPosition.setX(7);
                clickedPosition.setY(5);
                break;

            case R.id.R06:
                clickedPosition.setX(0);
                clickedPosition.setY(6);
                break;
            case R.id.R16:
                clickedPosition.setX(1);
                clickedPosition.setY(6);
                break;
            case R.id.R26:
                clickedPosition.setX(2);
                clickedPosition.setY(6);
                break;
            case R.id.R36:
                clickedPosition.setX(3);
                clickedPosition.setY(6);
                break;
            case R.id.R46:
                clickedPosition.setX(4);
                clickedPosition.setY(6);
                break;
            case R.id.R56:
                clickedPosition.setX(5);
                clickedPosition.setY(6);
                break;
            case R.id.R66:
                clickedPosition.setX(6);
                clickedPosition.setY(6);
                break;
            case R.id.R76:
                clickedPosition.setX(7);
                clickedPosition.setY(6);
                break;

            case R.id.R07:
                clickedPosition.setX(0);
                clickedPosition.setY(7);
                break;
            case R.id.R17:
                clickedPosition.setX(1);
                clickedPosition.setY(7);
                break;
            case R.id.R27:
                clickedPosition.setX(2);
                clickedPosition.setY(7);
                break;
            case R.id.R37:
                clickedPosition.setX(3);
                clickedPosition.setY(7);
                break;
            case R.id.R47:
                clickedPosition.setX(4);
                clickedPosition.setY(7);
                break;
            case R.id.R57:
                clickedPosition.setX(5);
                clickedPosition.setY(7);
                break;
            case R.id.R67:
                clickedPosition.setX(6);
                clickedPosition.setY(7);
                break;
            case R.id.R77:
                clickedPosition.setX(7);
                clickedPosition.setY(7);
                break;
        }
        Log.d("sfre", "onClick: "+step);
        if(step<blueTeam.size()) {
            //setColorAtAllowedPlace();
            if(placeIsAllowed(clickedPosition)) {
                Board[clickedPosition.getX()][clickedPosition.getY()].setPiece(blueTeam.get(step));
                setBoard();
                step++;
                try {
                    messageTexte = "Blue team, place your " + blueTeam.get(step).getName();
                    if(blueTeam.get(step).getName().contains("M"))
                        messageBoard.setBackgroundResource(R.drawable.melee_blue);
                    else if(blueTeam.get(step).getName().contains("h"))
                        messageBoard.setBackgroundResource(R.drawable.tank_blue);
                    else if(blueTeam.get(step).getName().contains("i"))
                        messageBoard.setBackgroundResource(R.drawable.aircraft_blue);
                } catch (Exception e) {
                    messageTexte = "Red team, place your base";
                    messageBoard.setBackgroundResource(R.drawable.base_red);
                    resetColor();
                    setColorAtAllowedPlace();
                }
            }
        }
        else if(step-blueTeam.size()<redTeam.size()) {
            //setColorAtAllowedPlace();
            if(placeIsAllowed(clickedPosition)) {
                Board[clickedPosition.getX()][clickedPosition.getY()].setPiece(redTeam.get(step - blueTeam.size()));
                setBoard();
                step++;
                try {
                    messageTexte = "Red team, place your " + redTeam.get(step - blueTeam.size()).getName();
                    if(redTeam.get(step-blueTeam.size()).getName().contains("M"))
                        messageBoard.setBackgroundResource(R.drawable.melee_red);
                    else if(redTeam.get(step-blueTeam.size()).getName().contains("h"))
                        messageBoard.setBackgroundResource(R.drawable.tank_red);
                    else if(redTeam.get(step-blueTeam.size()).getName().contains("i"))
                        messageBoard.setBackgroundResource(R.drawable.aircraft_red);
                } catch (Exception e) {
                    messageTexte = "BLUE TURN";
                    messageBoard.setVisibility(View.INVISIBLE);
                    gameStart = true;
                    resetColor();
                }
            }
        }
        message.setText(messageTexte);

        if(gameStart) {
            button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isMoved) {
                        button.setText("ShOOT");
                        FirstPlayerTurn = !FirstPlayerTurn;
                        if(FirstPlayerTurn){
                            messageTexte = "RED TURN";
                        }
                        else
                            messageTexte = "BLUE TURN";
                        message.setText(messageTexte);
                        isMoved = false;
                    } else {
                        button.setText("END TURN");
                        isMoved = true;
                        for (int i = 0; i < blueTeam.size(); i++) {
                            blueTeam.get(i).reset();
                        }
                        for (int i = 0; i < redTeam.size(); i++) {
                            redTeam.get(i).reset();
                        }
                        //TODO mettre une variable isMoved dans Unity ansi qu'une fonction pour la reset. Bloquer le mvmnt une fois bouger, reset ICI
                    }
                }
            });

            if (!AnythingSelected) { // phase de sélection de la troupe
                if (Board[clickedPosition.getX()][clickedPosition.getY()].getUnity() == null) {
                    return;
                } else {
                    if (Board[clickedPosition.getX()][clickedPosition.getY()].getUnity().isBlue() != FirstPlayerTurn) {
                        //isKingInDanger();
                        return;
                    } else {
                        listOfCoordinates.clear();
                        if (!isMoved && !Board[clickedPosition.getX()][clickedPosition.getY()].getUnity().getHasMoved()) {
                            listOfCoordinates = Board[clickedPosition.getX()][clickedPosition.getY()].getUnity().AllowedMoves(clickedPosition, Board);
                            DisplayBoardBackground[clickedPosition.getX()][clickedPosition.getY()].setBackgroundResource(R.color.colorSelected);
                            setColorAtAllowedPosition(listOfCoordinates);
                        } else if (isMoved && !Board[clickedPosition.getX()][clickedPosition.getY()].getUnity().getHasAttacked()) {
                            listOfCoordinates = Board[clickedPosition.getX()][clickedPosition.getY()].getUnity().AllowedShoot(clickedPosition, Board);
                            setColorAtAllowedTarget(listOfCoordinates);
                        }
                        AnythingSelected = true;
                    }
                }
            } else if (!isMoved) {
                if (Board[clickedPosition.getX()][clickedPosition.getY()].getUnity() == null) { //si ou on a cliqué, il n'y a pas d'obstacle
                    if (moveIsAllowed(listOfCoordinates, clickedPosition)) { // si le move est possible

                        saveBoard();
                        Board[lastPos.getX()][lastPos.getY()].getUnity().setHasMoved();
                        Board[clickedPosition.getX()][clickedPosition.getY()].setPiece(Board[lastPos.getX()][lastPos.getY()].getUnity());
                        Board[lastPos.getX()][lastPos.getY()].setPiece(null);

                        resetColorAtAllowedPosition(listOfCoordinates);
                        DisplayBoard[lastPos.getX()][lastPos.getY()].setBackgroundResource(0);
                        resetColorAtLastPosition(lastPos);
                        AnythingSelected = false;


                    } else { // si move impossible
                        resetColorAtLastPosition(lastPos);
                        resetColorAtAllowedPosition(listOfCoordinates);
                        AnythingSelected = false;
                    }

                } else {
                    resetColorAtLastPosition(lastPos);
                    resetColorAtAllowedPosition(listOfCoordinates);
                    AnythingSelected = false;
                }

            } else {
                if (shootIsAllowed(listOfCoordinates, clickedPosition)) {
                    Log.d("shotTest", "onClick: ça marche poupée");
                    saveBoard();
                    Board[lastPos.getX()][lastPos.getY()].getUnity().attackUnity(Board[clickedPosition.getX()][clickedPosition.getY()].getUnity());
                    if (!Board[clickedPosition.getX()][clickedPosition.getY()].getUnity().getIsAlive()) {
                        Board[clickedPosition.getX()][clickedPosition.getY()].setPiece(null);
                    }
                    resetColorAtAllowedPosition(listOfCoordinates);
                    DisplayBoard[lastPos.getX()][lastPos.getY()].setBackgroundResource(0);
                    resetColorAtLastPosition(lastPos);
                    AnythingSelected = false;
                } else {
                    Log.d("shotTest", "onClick: ça marche pas");
                    resetColorAtLastPosition(lastPos);
                    resetColorAtAllowedPosition(listOfCoordinates);
                    AnythingSelected = false;
                }
            }

            //isKingInDanger();
            lastPos = new Coordinates(clickedPosition.getX(), clickedPosition.getY());
            setBoard();
        }
    }

    public void saveBoard(){
        numberOfMoves++;
        LastMoves.add(numberOfMoves-1 ,Board2 );

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                LastMoves.get(numberOfMoves-1)[i][j] = new Position(null);
            }
        }

        for(int g=0;g<8;g++){
            for(int h=0;h<8;h++){
                if(Board[g][h].getUnity()==null){
                    LastMoves.get(numberOfMoves-1)[g][h].setPiece(null);
                }else{
                    LastMoves.get(numberOfMoves-1)[g][h].setPiece(Board[g][h].getUnity());
                }
            }
        }
    }
    private void resetColorAtAllowedPosition(ArrayList<Coordinates> listOfCoordinates) {
        for(int i=0; i<listOfCoordinates.size(); i++){
            if((listOfCoordinates.get(i).getX() + listOfCoordinates.get(i).getY())%2==0){
                DisplayBoardBackground[listOfCoordinates.get(i).getX()][listOfCoordinates.get(i).getY()].setBackgroundResource(R.color.colorBoardDark);
            }else {
                DisplayBoardBackground[listOfCoordinates.get(i).getX()][listOfCoordinates.get(i).getY()].setBackgroundResource(R.color.colorBoardLight);
            }
        }
    }

    private void resetColor() {
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                if ((i + j) % 2 == 0) {
                    DisplayBoardBackground[i][j].setBackgroundResource(R.color.colorBoardDark);
                } else {
                    DisplayBoardBackground[i][j].setBackgroundResource(R.color.colorBoardLight);
                }
            }
        }
    }

    void setColorAtAllowedPosition(ArrayList<Coordinates> list){

        for(int i=0; i<list.size(); i++){
            if(Board[list.get(i).getX()][list.get(i).getY()].getUnity() == null){
                DisplayBoardBackground[list.get(i).getX()][list.get(i).getY()].setBackgroundResource(R.color.colorPositionAvailable);
            }else{
                DisplayBoardBackground[list.get(i).getX()][list.get(i).getY()].setBackgroundResource(R.color.colorDanger);
            }
    }
    }

    void setColorAtAllowedTarget(ArrayList<Coordinates> list){

        for(int i=0; i<list.size(); i++){
            if(Board[list.get(i).getX()][list.get(i).getY()].getUnity() == null){
                DisplayBoardBackground[list.get(i).getX()][list.get(i).getY()].setBackgroundResource(R.color.noTarget);
            }else{
                DisplayBoardBackground[list.get(i).getX()][list.get(i).getY()].setBackgroundResource(R.color.getTarget);
            }
        }
    }

    void setColorAtAllowedPlace(){

        if(step<blueTeam.size()){
            for(int i = 0; i<8; i++){
                for(int j = 0; j<4; j++){
                    if(Board[i][j].getUnity() == null)
                        DisplayBoardBackground[i][j].setBackgroundResource(R.color.blueZone);
                }
            }
        }
        if(step>=blueTeam.size()){
            for(int i = 0; i<8; i++){
                for(int j = 4; j<8; j++){
                    if(Board[i][j].getUnity() == null)
                        DisplayBoardBackground[i][j].setBackgroundResource(R.color.redZone);
                }
            }
        }
    }


    private boolean moveIsAllowed(ArrayList<Coordinates> piece, Coordinates coordinate) {
        Boolean Allowed = false;
        for(int i =0;i<piece.size();i++){
            if(piece.get(i).getX() == coordinate.getX()  &&  piece.get(i).getY() == coordinate.getY()){
                Allowed = true;
                break;
            }
        }
        return Allowed;
    }

    private boolean shootIsAllowed(ArrayList<Coordinates> piece, Coordinates coordinate) {
        Boolean Allowed = false;
        for(int i =0;i<piece.size();i++){
            if(piece.get(i).getX() == coordinate.getX()  &&  piece.get(i).getY() == coordinate.getY() && Board[coordinate.getX()][coordinate.getY()].getUnity() != null){
                if((Board[coordinate.getX()][coordinate.getY()].getUnity().isBlue() && !FirstPlayerTurn) ||
                        (!Board[coordinate.getX()][coordinate.getY()].getUnity().isBlue() && FirstPlayerTurn) ) {
                    Allowed = true;
                    break;
                }
            }
        }
        return Allowed;
    }

    private boolean placeIsAllowed(Coordinates coordinate){
        Boolean Allowed = false;
        if(step<blueTeam.size()){
            if(coordinate.getY()<4){
                Allowed = true;
                if(Board[coordinate.getX()][coordinate.getY()].getUnity()!=null)
                    return false;
            }
        }
        if(step>=blueTeam.size()){
            if(coordinate.getY()>3){
                Allowed = true;
                if(Board[coordinate.getX()][coordinate.getY()].getUnity()!=null)
                    return false;
            }
        }
        return Allowed;
    }
    private void resetColorAtLastPosition(Coordinates lastPos){
        if((lastPos.getX() + lastPos.getY())%2==0){
            DisplayBoardBackground[lastPos.getX()][lastPos.getY()].setBackgroundResource(R.color.colorBoardDark);
        }else {
            DisplayBoardBackground[lastPos.getX()][lastPos.getY()].setBackgroundResource(R.color.colorBoardLight);
        }
    }
}
