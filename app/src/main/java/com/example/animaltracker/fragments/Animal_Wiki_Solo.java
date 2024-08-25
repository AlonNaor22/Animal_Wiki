package com.example.animaltracker.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animaltracker.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Animal_Wiki_Solo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Animal_Wiki_Solo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Animal_Wiki_Solo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Animal_Wiki_Solo.
     */
    // TODO: Rename and change types and number of parameters
    public static Animal_Wiki_Solo newInstance(String param1, String param2) {
        Animal_Wiki_Solo fragment = new Animal_Wiki_Solo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_animal__wiki__solo, container, false);

        // Get the passed arguments
        if (getArguments() != null) {
            String animalName = getArguments().getString("animal_name");
            String animalHebrewName = getArguments().getString("animal_hebrew_name");
            String animalEndangeredLevel = getArguments().getString("animal_endangered_level");
            String animalType = getArguments().getString("animal_type");
            Integer animalImage = getArguments().getInt("animal_image");

            // Find your views (replace with actual view ids)
            TextView nameTextView = view.findViewById(R.id.name_textview);
            TextView hebrewNameTextView = view.findViewById(R.id.hebrew_name_textview);
            TextView endangeredTextView = view.findViewById(R.id.endangered_textview);
            TextView typeTextView = view.findViewById(R.id.type_textview);
            ImageView animalImageView = view.findViewById(R.id.animal_imageview);

            // Set the text to display animal details
            nameTextView.setText("Name: " + animalName);
            hebrewNameTextView.setText("Hebrew Name: " + animalHebrewName);
            endangeredTextView.setText("Endangered Level: " + animalEndangeredLevel);
            typeTextView.setText("Type: " + animalType);
            animalImageView.setImageResource(animalImage);
        }

        return view;
    }
}