package com.example.georgesamuel.cahtapp.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.georgesamuel.cahtapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        init();
        return view;
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mUser != null){
            Navigation.findNavController(getView()).navigate(R.id.action_splashFragment_to_mainGraph);
        }
        else{
            Navigation.findNavController(getView()).navigate(R.id.action_splashFragment_to_loginFragment);
        }
    }
}
