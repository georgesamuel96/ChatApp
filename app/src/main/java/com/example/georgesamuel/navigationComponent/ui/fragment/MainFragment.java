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
public class MainFragment extends Fragment {


    @BindView(R.id.back)
    MaterialButton back;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        // if you use this line -- you will exit from application
        //getActivity().finish();


        Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_mainGraph_pop);
    }
}
