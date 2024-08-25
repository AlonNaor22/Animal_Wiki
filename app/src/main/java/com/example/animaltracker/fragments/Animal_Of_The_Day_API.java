package com.example.animaltracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import com.example.animaltracker.R;
import okhttp3.Response;

public class Animal_Of_The_Day_API extends Fragment {

    private TextView animalName, taxonomyKingdom, taxonomyPhylum,
            taxonomyClass, taxonomyOrder, taxonomyFamily;
    private TextView animalLocations;
    private static String[] animalNames = {
            "Aardvark", "Albatross", "Alligator", "Anaconda", "Ant", "Armadillo", "Baboon", "Badger", "Barracuda", "Bat",
            "Bear", "Beaver", "Bee", "Bison", "Boar", "Buffalo", "Butterfly", "Camel", "Capybara", "Caribou",
            "Cassowary", "Cat", "Caterpillar", "Cheetah", "Chicken", "Chimpanzee", "Chinchilla", "Clownfish", "Cobra",
            "Cockroach", "Coyote", "Crab", "Crocodile", "Crow", "Deer", "Dingo", "Dolphin", "Donkey", "Dove",
            "Dragonfly", "Eagle", "Echidna", "Elephant", "Emu", "Falcon", "Ferret", "Flamingo", "Fox", "Frog", "Gazelle"
    };

    private static int randomIndex = (int) (Math.random() * animalNames.length); // Generates a random number between 0 (inclusive) and animalNames.length (exclusive)

    private static final String API_URL = "https://api.api-ninjas.com/v1/animals?name="+animalNames[randomIndex];
    private static final String API_KEY = "OFimxIHK2ueaXMhwuOIP6w==PBpYr1zDrSl5W8cM";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animal_of_the_day_api, container, false);

        // Initialize the TextViews from the layout
        animalName = view.findViewById(R.id.animal_name);
        taxonomyKingdom = view.findViewById(R.id.taxonomy_kingdom);
        taxonomyPhylum = view.findViewById(R.id.taxonomy_phylum);
        taxonomyClass = view.findViewById(R.id.taxonomy_class);
        taxonomyOrder = view.findViewById(R.id.taxonomy_order);
        taxonomyFamily = view.findViewById(R.id.taxonomy_family);
        animalLocations = view.findViewById(R.id.animal_locations);

        // Fetch animal information automatically when the fragment is created
        fetchAnimalData();

        return view;
    }

    private void fetchAnimalData() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("X-Api-Key", API_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseData = response.body().string();
                    getActivity().runOnUiThread(() -> {
                        try {
                            JSONArray jsonArray = new JSONArray(responseData);
                            if (jsonArray.length() > 0) {
                                JSONObject animalObject = jsonArray.getJSONObject(0);
                                displayAnimalData(animalObject);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        });
    }

    private void displayAnimalData(JSONObject animalObject) throws JSONException {
        animalName.setText(animalObject.getString("name"));

        // Taxonomy
        JSONObject taxonomy = animalObject.getJSONObject("taxonomy");
        taxonomyKingdom.setText("Kingdom: " + taxonomy.getString("kingdom"));
        taxonomyPhylum.setText("Phylum: " + taxonomy.getString("phylum"));
        taxonomyClass.setText("Class: " + taxonomy.getString("class"));
        taxonomyOrder.setText("Order: " + taxonomy.getString("order"));
        taxonomyFamily.setText("Family: " + taxonomy.getString("family"));

        // Locations
        JSONArray locationsArray = animalObject.getJSONArray("locations");
        StringBuilder locations = new StringBuilder();
        for (int i = 0; i < locationsArray.length(); i++) {
            locations.append(locationsArray.getString(i));
            if (i < locationsArray.length() - 1) {
                locations.append(", ");
            }
        }
        animalLocations.setText("Locations: " + locations.toString());
    }
}
