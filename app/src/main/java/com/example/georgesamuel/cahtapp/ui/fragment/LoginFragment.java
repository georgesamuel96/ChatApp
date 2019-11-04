package com.example.georgesamuel.cahtapp.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.georgesamuel.cahtapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_email)
    TextInputLayout etEmail;
    @BindView(R.id.et_password)
    TextInputLayout etPassword;
    @BindView(R.id.btn_forgetPassword)
    MaterialButton btnForgetPassword;
    @BindView(R.id.btn_login)
    MaterialButton btnLogin;
    @BindView(R.id.btn_register_new_account)
    MaterialButton btnRegisterNewAccount;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btn_forgetPassword, R.id.btn_login, R.id.btn_register_new_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_forgetPassword:
                break;
            case R.id.btn_login:
                break;
            case R.id.btn_register_new_account:
                Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_registerFragment);
        }
    }
}
