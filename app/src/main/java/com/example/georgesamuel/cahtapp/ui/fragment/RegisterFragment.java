package com.example.georgesamuel.cahtapp.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.georgesamuel.cahtapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_name)
    TextInputLayout etName;
    @BindView(R.id.et_email)
    TextInputLayout etEmail;
    @BindView(R.id.et_password)
    TextInputLayout etPassword;
    @BindView(R.id.et_confirm_pass)
    TextInputLayout etConfirmPass;
    @BindView(R.id.btn_register)
    MaterialButton btnRegister;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }

    @OnClick(R.id.btn_register)
    public void onRegisterClicked() {
        final String name = etName.getEditText().getText().toString().trim();
        final String email = etEmail.getEditText().getText().toString().trim();
        final String password = etPassword.getEditText().getText().toString();
        final String confirmPassword = etConfirmPass.getEditText().getText().toString();
        if(!password.equals(confirmPassword)){
        }
    }
}
