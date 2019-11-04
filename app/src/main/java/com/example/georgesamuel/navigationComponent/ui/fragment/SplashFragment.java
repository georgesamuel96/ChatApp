package com.example.georgesamuel.navigationComponent.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.georgesamuel.navigationComponent.R;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {


    @BindView(R.id.click)
    MaterialButton click;

    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.click)
    public void onViewClicked() {
        Navigation.findNavController(getView()).navigate(R.id.action_splashFragment_to_registerFragment);
    }
}
