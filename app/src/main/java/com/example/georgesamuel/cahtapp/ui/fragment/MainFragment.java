package com.example.georgesamuel.cahtapp.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.georgesamuel.cahtapp.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private FirebaseAuth mAuth;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        mAuth = FirebaseAuth.getInstance();
        return view;
    }

    @OnClick(R.id.btn_signout)
    public void onViewClicked() {
        mAuth.signOut();
    }
}
