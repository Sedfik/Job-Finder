package com.example.job_finder;


import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getOffers(JSONutils.loadJSONFromAsset("offres_emploi", this));
    }

    /**
     * Gestion des evenements pour la navigation inter menu
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;

                        case R.id.nav_map:
                            selectedFragment = new MapInfoFragment();
                            break;

                        case R.id.nav_info:
                            selectedFragment = new FavoriteFragment();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };


    public void getOffers(String data) {
        /** Recupreration et affichages des offres d'emploi**/
        try {
            JSONObject jsonData = new JSONObject(data);

            JSONArray results = jsonData.getJSONArray("resultats");

            for (int i = 0; i < results.length(); i++) {
                JSONObject jsonOffer = results.getJSONObject(i);
                JSONObject location = jsonOffer.getJSONObject("lieuTravail");
                Log.d("Bob", location.toString());
                double longitude = Double.parseDouble(location.getString("longitude"));
                double latitude = Double.parseDouble(location.getString("latitude"));


                // Recuperation des champs utiles
                String id = jsonOffer.getString("id");

                String intitule = jsonOffer.getString("intitule");

                String salaire = jsonOffer.getJSONObject("salaire").getString("libelle");

                String typeContrat = jsonOffer.getString("typeContratLibelle");

                String url_postulation = jsonOffer.getJSONObject("origineOffre").getString("urlOrigine");

                Offer offer = new Offer(id, intitule, "", latitude, longitude, "", "", typeContrat, salaire, url_postulation);

                OfferListSingleton.addOffer(offer);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
