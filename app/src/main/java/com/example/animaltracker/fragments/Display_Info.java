package com.example.animaltracker.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

    //the function update according to the user decision and make sure the new information is valid
    private void updateUserInfo(View frag_view) {
        // Get Firebase user and reference
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Toast.makeText(getContext(), "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }
        String uid = user.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(uid);

        // Get references to UI elements
        EditText updateText = frag_view.findViewById(R.id.update_text);
        RadioGroup radioGroup = frag_view.findViewById(R.id.radioGroup);

        String newText = updateText.getText().toString().trim();

        // Get selected radio button ID
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // If no radio button is selected
        if (selectedId == -1) {
            Toast.makeText(getContext(), "Please select a field to update", Toast.LENGTH_SHORT).show();
            return;
        }

        // Handle the selected radio button (Phone or Name only)
        if (selectedId == R.id.radioButtonPhone) {
            if (!newText.matches("\\d{10}")) {
                Toast.makeText(getContext(), "Phone must contain exactly 10 digits", Toast.LENGTH_SHORT).show();
                return;
            }
            // Update phone TextView and Firebase
            TextView phoneTextView = frag_view.findViewById(R.id.textViewPhone);
            phoneTextView.setText(newText);
            userRef.child("phone").setValue(newText);
        } else if (selectedId == R.id.radioButtonName) {
            if (!newText.matches("[a-zA-Z\\s]+")) {
                Toast.makeText(getContext(), "Name can only contain letters and spaces", Toast.LENGTH_SHORT).show();
                return;
            }
            // Update name TextView and Firebase
            TextView nameTextView = frag_view.findViewById(R.id.textViewName);
            nameTextView.setText(newText);
            userRef.child("name").setValue(newText);
        }
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

        Button updateButton = view.findViewById(R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserInfo(view);
            }
        });


        return view;
    }

    //update the fragment with the current user information
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
