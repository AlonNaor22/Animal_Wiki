package com.example.animaltracker.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.animaltracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Display_Info extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private TextView textViewEmail, textViewName, textViewPhone, textViewPassword;

    public Display_Info() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display__info, container, false);

        textViewEmail = view.findViewById(R.id.textViewEmail);
        textViewName = view.findViewById(R.id.textViewName);
        textViewPhone = view.findViewById(R.id.textViewPhone);
        textViewPassword = view.findViewById(R.id.textViewPassword);  // New TextView for password

        // Fetch and display current user info
        loadCurrentUserInfo();

        return view;
    }

    private void loadCurrentUserInfo() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();

            // Retrieve user information from the database
            databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String email = dataSnapshot.child("email").getValue(String.class);
                        String name = dataSnapshot.child("name").getValue(String.class);
                        String phone = dataSnapshot.child("phone").getValue(String.class);
                        String password = dataSnapshot.child("password").getValue(String.class);  // Retrieving password

                        textViewEmail.setText(email);
                        textViewName.setText(name);
                        textViewPhone.setText(phone);
                        textViewPassword.setText(password);  // Setting password
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle database error
                }
            });
        }
    }
}
