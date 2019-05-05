package com.example.androchess;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

import com.example.androchess.R;

public class LoginFragment extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        onClick(view);
        return view;
    }

    public static Fragment newInstance() {
        LoginFragment mFrgment = new LoginFragment();
        return mFrgment;
    }

    void onClick(View view){
        Button loginButton = (Button) view.findViewById(R.id.btn_login);
        final EditText pseudo = (EditText) view.findViewById(R.id.input_pseudo);
        final EditText password = (EditText) view.findViewById(R.id.input_password);
        TextView signUpLink = (TextView) view.findViewById(R.id.link_signup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pseudo.getText().toString().isEmpty()||password.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "please insert both name and password", Toast.LENGTH_SHORT).show();
                }
                else{
                    User user = new User(pseudo.getText().toString(), password.getText().toString());
                    //ici faut check si il est dans la db
                    //si oui et que match avec le password, alors avancer
                    //sinon donner une erreur
                    if(userDB.checkIfUserMatchs(user)==true){
                        Intent intent = new Intent(getActivity(),MenuActivity.class);
                        intent.putExtra("user", user);
                        Toast.makeText(getActivity(), "In db, switching activity", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getActivity(), "Not in database, please create an account", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.Fragment fg = SignUpFragment.newInstance(pseudo.getText().toString());
                // adding fragment to relative layout by using layout id
                getFragmentManager().beginTransaction().replace(R.id.fragment, fg).addToBackStack(null).commit();
            }
        });
    }
}
