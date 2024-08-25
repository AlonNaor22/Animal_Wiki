package com.example.animaltracker.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.animaltracker.R;
import com.example.animaltracker.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sign_In#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sign_In extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseAuth mAuth;

    public Sign_In() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sign_In.
     */
    // TODO: Rename and change types and number of parameters
    public static Sign_In newInstance(String param1, String param2) {
        Sign_In fragment = new Sign_In();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void sign_in_func(View view){

        EditText emailT = view.findViewById(R.id.NewEmailText);
        EditText passwordT = view.findViewById(R.id.NewPasswordText);
        String email = emailT.getText().toString();
        String password = passwordT.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getContext(), "Sign In Successful", Toast.LENGTH_LONG).show();
                            addData(view);
                            Navigation.findNavController(view).navigate(R.id.action_sign_In_to_log_In);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getContext(), "Sign In Failed", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    private boolean check_info(View frag_view) {
        // Get user inputs
        EditText emailEditText = frag_view.findViewById(R.id.NewEmailText);
        EditText phoneEditText = frag_view.findViewById(R.id.NewPhoneText);
        EditText passwordEditText = frag_view.findViewById(R.id.NewPasswordText);
        EditText fullNameEditText = frag_view.findViewById(R.id.NewNameText);

        String email = emailEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String fullName = fullNameEditText.getText().toString().trim();

        // Check email
        if (!email.endsWith(".com") && !email.endsWith(".co.il")) {
            Toast.makeText(getContext(), "Email must end with .com or .co.il", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!email.contains("@") || email.indexOf("@") == 0) {
            Toast.makeText(getContext(), "Email must contain '@' and it cannot be the first character", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check phone
        if (!phone.matches("\\d{10}")) {
            Toast.makeText(getContext(), "Phone must contain exactly 10 digits", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check password
        if (!password.matches("[a-zA-Z0-9]{6,12}")) {
            Toast.makeText(getContext(), "Password must be between 6 to 12 characters and contain only English letters or numbers", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check full name
        if (!fullName.matches("[a-zA-Z\\s]+")) {
            Toast.makeText(getContext(), "Full name can only contain letters and spaces", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    public void addData(View view){
        EditText emailT = view.findViewById(R.id.NewEmailText);
        EditText passwordT = view.findViewById(R.id.NewPasswordText);
        EditText phoneT = view.findViewById(R.id.NewPhoneText);
        EditText nameT = view.findViewById(R.id.NewNameText);

        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(uid);

        UserModel u = new UserModel(emailT.getText().toString(), passwordT.getText().toString(),
                phoneT.getText().toString(), nameT.getText().toString());

        myRef.setValue(u);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //fragment view
        View frag_view =  inflater.inflate(R.layout.fragment_sign__in, container, false);

        //create account button
        Button create_button = (Button) frag_view.findViewById(R.id.create_account_button);
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_info(frag_view)) {
                    sign_in_func(frag_view);
                }
            }
        });


        return frag_view;
    }
}