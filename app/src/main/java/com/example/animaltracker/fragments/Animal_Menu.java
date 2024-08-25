package com.example.animaltracker.fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.animaltracker.R;
import com.example.animaltracker.models.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Animal_Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Animal_Menu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseAuth mAuth;



    public Animal_Menu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Animal_Menu.
     */
    // TODO: Rename and change types and number of parameters
    public static Animal_Menu newInstance(String param1, String param2) {
        Animal_Menu fragment = new Animal_Menu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void getDATA(View view){
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(uid);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                UserModel value = dataSnapshot.getValue(UserModel.class);
                Toast.makeText(getContext(), value.getName(), Toast.LENGTH_LONG).show();


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frag_view =  inflater.inflate(R.layout.fragment_animal__menu, container, false);


        //display button
        Button display_button = (Button) frag_view.findViewById(R.id.display_info_button);
        display_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDATA(frag_view);
                Navigation.findNavController(frag_view).navigate(R.id.action_animal_Menu_to_display_Info);
            }
        });

        //recycleview button
        Button sign_button = (Button) frag_view.findViewById(R.id.display_animal_list);
        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(frag_view).navigate(R.id.action_animal_Menu_to_animals_recycleview);
            }
        });


        //animal of the day button
        Button day_button = (Button) frag_view.findViewById(R.id.display_animal_of_the_day);
        day_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(frag_view).navigate(R.id.action_animal_Menu_to_API_Menu);
            }
        });



        return frag_view;
    }
}