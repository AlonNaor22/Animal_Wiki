package com.example.animaltracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animaltracker.R;
import com.example.animaltracker.models.AnimalModel;

import java.util.ArrayList;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.MyViewHolder> {

    private ArrayList<AnimalModel> dataSet;

    public AnimalAdapter(ArrayList<AnimalModel> dataSet){ this.dataSet=dataSet;}




    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNameEnglish;
        TextView textViewNameHebrew;
        ImageView imageView;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.animal_card);
            textViewNameEnglish = itemView.findViewById(R.id.name_english);
            textViewNameHebrew = itemView.findViewById(R.id.name_hebrew);
            imageView = itemView.findViewById(R.id.animal_Image);
        }
    }




    @NonNull
    @Override
    public AnimalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_cardview, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalAdapter.MyViewHolder holder, int position) {

        TextView textViewNameEnglish = holder.textViewNameEnglish;
        TextView textViewNameHebrew = holder.textViewNameHebrew;
        ImageView imageView = holder.imageView;

        textViewNameEnglish.setText(dataSet.get(position).getName());
        textViewNameHebrew.setText(dataSet.get(position).getHname());
        imageView.setImageResource(dataSet.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
