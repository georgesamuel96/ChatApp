package com.example.georgesamuel.cahtapp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.georgesamuel.cahtapp.R;
import com.example.georgesamuel.cahtapp.Utils;
import com.example.georgesamuel.cahtapp.model.User;
import com.example.georgesamuel.cahtapp.ui.activity.SplashActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    @BindView(R.id.image_person)
    CircleImageView imagePerson;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDBReference;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        getUser();
        initBottomNavigation();
    }

    private void initBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.action_chat){
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_frame, new ChatFragment())
                        .commit();
            }
            else if(item.getItemId() == R.id.action_users){
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_frame, new UsersFragment())
                        .addToBackStack(null)
                        .commit();
            }
            else if(item.getItemId() == R.id.action_notification){
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_frame, new NotificationsFragment())
                        .addToBackStack(null)
                        .commit();
            }
            return true;
        });
        bottomNavigation.setSelectedItemId(0);
    }

    private void getUser() {
        mDBReference = FirebaseDatabase.getInstance().getReference(Utils.USERS).child(currentUser.getUid());
        mDBReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                tvName.setText(user.getName());
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.ic_person);
                Glide.with(getContext())
                        .applyDefaultRequestOptions(requestOptions)
                        .load(user.getImageUrl())
                        .into(imagePerson);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void signout() {
        mAuth.signOut();
        startActivity(new Intent(getContext(), SplashActivity.class));
        getActivity().finish();
    }

    @OnClick(R.id.image_logout)
    public void onLogoutClicked() {
        new MaterialAlertDialogBuilder(getContext())
                .setMessage(getString(R.string.logout_message))
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> signout())
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss())
                .show();
    }
}
