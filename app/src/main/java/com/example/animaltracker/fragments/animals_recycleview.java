package com.example.animaltracker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animaltracker.R;
import com.example.animaltracker.adapters.AnimalAdapter;
import com.example.animaltracker.models.AnimalModel;
import com.example.animaltracker.services.MyData;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link animals_recycleview#newInstance} factory method to
 * create an instance of this fragment.
 */
public class animals_recycleview extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //recyclerview data
    private ArrayList<AnimalModel> dataSet;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AnimalAdapter adapter;



    public animals_recycleview() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment animals_recycleview.
     */
    // TODO: Rename and change types and number of parameters
    public static animals_recycleview newInstance(String param1, String param2) {
        animals_recycleview fragment = new animals_recycleview();
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frag_view =  inflater.inflate(R.layout.fragment_animals_recycleview, container, false);

        //initializing for recyclerview
        recyclerView = frag_view.findViewById(R.id.recycle_view_source);
        layoutManager = new LinearLayoutManager(frag_view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dataSet = new ArrayList<>();

        //add info for arraylist
        for (int i = 0; i< MyData.idArray.length; i++){
            dataSet.add(new AnimalModel(MyData.idArray[i],
                    MyData.nameArray[i], MyData.HnameArray[i],
                    MyData.typeArray[i], MyData.HtypeArray[i],
                    MyData.endangeredLevelArray[i],
                    MyData.HendangeredLevelArray[i],
                    MyData.drawableArray[i]));
                    //MyData.imageUrlArray[i]));
        }

        adapter = new AnimalAdapter(dataSet);
        recyclerView.setAdapter(adapter);






        return frag_view;
    }
}