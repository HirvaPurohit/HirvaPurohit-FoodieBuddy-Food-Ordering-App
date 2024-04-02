package com.example.foodorderingapp;


import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class frag_profile extends Fragment {


    Button btnedit,btnlogout;
    TextView txtname,txtemail,txtnumber;
    public String P_Name,P_email,P_phone;
    Boolean aBoolean;

    FirebaseAuth mAuth;
    FirebaseUser user;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public frag_profile() {
        // Required empty public constructor
    }

    public static frag_profile newInstance(String param1, String param2) {
        frag_profile fragment = new frag_profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment

        View  v =  inflater.inflate(R.layout.fragment_frag_profile, container, false);
//        btnedit = v.findViewById(R.id.btnedit);
        txtname = v.findViewById(R.id.txtname);
        txtemail = v.findViewById(R.id.txtemail);
        txtnumber = v.findViewById(R.id.txtnumber);
        btnlogout = v.findViewById(R.id.btnlogout);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if(user == null){
            Intent i = new Intent(getContext(),MainActivity.class);
            startActivity(i);

        }


//        btnedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getContext(), EditProfile_Activity.class);
//                startActivity(i);
//            }
//        });



        SharedPreferences sharedPreferencesR = getActivity().getSharedPreferences("userdata",MODE_PRIVATE);
        P_Name = sharedPreferencesR.getString("Name","");
        P_email = sharedPreferencesR.getString("Email","");
        P_phone = sharedPreferencesR.getString("Phone","");
//        aBoolean = sharedPreferences.getBoolean("login",false);

        txtname.setText(P_Name);
        txtemail.setText(P_email);
        txtnumber.setText(P_phone);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor =  sharedPreferencesR.edit();
                editor.clear();
                editor.apply();
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(), "LogOut Successfully", Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferencesL = getActivity().getSharedPreferences("logindata",MODE_PRIVATE);
                P_Name = sharedPreferencesL.getString("Email","");

                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);

            }
        });


        return v;
    }
}