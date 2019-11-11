package com.example.georgesamuel.cahtapp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.georgesamuel.cahtapp.R;
import com.example.georgesamuel.cahtapp.ui.activity.MainActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    @BindView(R.id.loading)
    ProgressBar loading;
    private FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
    }

    @OnClick({R.id.btn_forgetPassword, R.id.btn_login, R.id.btn_register_new_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_forgetPassword:
                break;
            case R.id.btn_login:
                setNoErrors();
                checkFields();
                break;
            case R.id.btn_register_new_account:
                Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_registerFragment);
        }
    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                   if(task.isSuccessful()) {
                       loading.setVisibility(View.INVISIBLE);
                       startActivity(new Intent(getContext(), MainActivity.class));
                       getActivity().finish();
                   }
                   else {
                       loading.setVisibility(View.INVISIBLE);
                       Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                   }
                });
    }

    private void setNoErrors() {
        etEmail.setErrorEnabled(false);
        etPassword.setErrorEnabled(false);
    }

    private void checkFields() {
        final String email = etEmail.getEditText().getText().toString().trim();
        final String password = etPassword.getEditText().getText().toString();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(getString(R.string.error_email));
        } else if (password.length() < 8) {
            etPassword.setError(getString(R.string.error_password));
        } else {
            loading.setVisibility(View.VISIBLE);
            login(email, password);
        }
    }
}
