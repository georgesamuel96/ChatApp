package com.example.georgesamuel.cahtapp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.georgesamuel.cahtapp.R;
import com.example.georgesamuel.cahtapp.Utils;
import com.example.georgesamuel.cahtapp.model.User;
import com.example.georgesamuel.cahtapp.ui.activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    private static final String TAG = RegisterFragment.class.getSimpleName();
    @BindView(R.id.loading)
    ProgressBar loading;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        initToolbar();
        mAuth = FirebaseAuth.getInstance();
        etPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 7)
                    etPassword.setErrorEnabled(false);
                else
                    etPassword.setErrorEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_registerFragment_pop);
        });
    }

    @OnClick(R.id.btn_register)
    public void onRegisterClicked() {
        setNoErrors();
        checkFields();
    }

    private void setNoErrors() {
        etName.setErrorEnabled(false);
        etEmail.setErrorEnabled(false);
        etPassword.setErrorEnabled(false);
        etConfirmPass.setErrorEnabled(false);
    }

    private void checkFields() {
        final String name = etName.getEditText().getText().toString().trim();
        final String email = etEmail.getEditText().getText().toString().trim();
        final String password = etPassword.getEditText().getText().toString();
        final String confirmPassword = etConfirmPass.getEditText().getText().toString();
        if (name.equals("")) {
            etName.setError(getString(R.string.error_name));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(getString(R.string.error_email));
        } else if (password.length() < 8) {
            etPassword.setError(getString(R.string.error_password));
        } else if (!password.equals(confirmPassword)) {
            etConfirmPass.setError(getString(R.string.error_confirm_password));
        } else {
            register(name, email, password);
        }
    }

    private void register(String name, String email, String password) {
        loading.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user, name, email);
                    } else {
                        loading.setVisibility(View.INVISIBLE);
                        Log.d(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(getContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUI(FirebaseUser user, String name, String email) {
        final String userId = user.getUid();
        mReference = FirebaseDatabase.getInstance().getReference(Utils.USERS).child(userId);
        User currentUser = new User();
        currentUser.setEmail(email);
        currentUser.setId(userId);
        currentUser.setImageUrl("default");
        currentUser.setName(name);
        mReference.setValue(currentUser).addOnCompleteListener(getActivity(), task -> {
            if (task.isSuccessful()) {
                loading.setVisibility(View.INVISIBLE);
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
            else{
                loading.setVisibility(View.INVISIBLE);
            }
        });
    }

    @OnClick({R.id.et_name, R.id.et_email, R.id.et_password, R.id.et_confirm_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_name:
                setNoErrors();
                break;
            case R.id.et_email:
                setNoErrors();
                break;
            case R.id.et_password:
                setNoErrors();
                break;
            case R.id.et_confirm_pass:
                setNoErrors();
                break;
        }
    }
}
