package com.example.animaltracker.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.animaltracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Log_In#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Log_In extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseAuth mAuth;

    public Log_In() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Log_In.
     */
    // TODO: Rename and change types and number of parameters
    public static Log_In newInstance(String param1, String param2) {
        Log_In fragment = new Log_In();
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

    public void log_in_func(View view){

        EditText emailT = view.findViewById(R.id.emailText);
        EditText passwordT = view.findViewById(R.id.passwordText);
        String email = emailT.getText().toString();
        String password = passwordT.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getContext(), "Log In Successful", Toast.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_log_In_to_animal_Menu);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getContext(), "Log In Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //fragment view
        View frag_view =  inflater.inflate(R.layout.fragment_log__in, container, false);

        //log in button
        Button log_button = (Button) frag_view.findViewById(R.id.log_in_button);
        log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log_in_func(frag_view);
            }
        });

        //sign in button
        Button sign_button = (Button) frag_view.findViewById(R.id.sign_in_button);
        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(frag_view).navigate(R.id.action_log_In_to_sign_In);
            }
        });



        return frag_view;
    }
}