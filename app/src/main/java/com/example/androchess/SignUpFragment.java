package com.example.androchess;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignUpFragment extends Fragment {
    private static String string;
    private UserDB userDB;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDB=new UserDB(getActivity());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        EditText edit = (EditText) view.findViewById(R.id.input_pseudo);
        edit.setText(string);
        onClick(view);
        return view;
    }

    public static Fragment newInstance(String texte) {
        SignUpFragment mFrgment = new SignUpFragment();
        string = texte;
        return mFrgment;
    }
    void onClick(View view){
        Button signUpButton = (Button) view.findViewById(R.id.btn_signUp);
        final EditText pseudo = (EditText) view.findViewById(R.id.input_pseudo);
        final EditText password = (EditText) view.findViewById(R.id.input_password);
        //TextView signUpLink = (TextView) view.findViewById(R.id.link_signup);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pseudo.getText().toString().isEmpty()||password.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "please insert both name and password", Toast.LENGTH_SHORT).show();
                }
                else{
                    User user = new User(pseudo.getText().toString(), password.getText().toString());
                    //ici il faut créer le user et l'insert dans la DB avec une team par défaut
                    //cependant si le pseudo choisi existe déjà dans la db, il faut ne pas l'ajouter et
                    //rendre un message d'erreur!
                    if(userDB.checkIfUserInDb(user)==true){
                        Toast.makeText(getActivity(), "Already in db, please try to connect", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        try{
                            Intent intent = new Intent(getActivity(), MenuActivity.class);
                            userDB.insertUser(user);
                            user=userDB.loadUser(user.getName());
                            intent.putExtra("user", user);
                            Toast.makeText(getActivity(), "Inserted in db, switching activity", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                        catch (Exception e){
                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                        }


                    }
                }
            }
        });
    }
}
