package com.example.job_finder;

import androidx.fragment.app.FragmentActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static java.security.AccessController.getContext;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private SupportMapFragment map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        map.getMapAsync(this);

    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near paris, france.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap map) {

        // Add a marker in paris and move the camera
        LatLng paris = new LatLng(48.864716, 2.349014);
        map.moveCamera(CameraUpdateFactory.newLatLng(paris));

        String data = JSONutils.loadJSONFromAsset("offres_emploi", this );
        //Log.d("Info",data)

        /** Recupreration et affichages des offres d'emploi**/
        try {
            JSONObject jsonData = new JSONObject(data);

            JSONArray results = jsonData.getJSONArray("resultats");

            for(int i = 0; i < results.length(); i++){
                JSONObject location = results.getJSONObject(i).getJSONObject("lieuTravail");
                Log.d("Info", location.toString());
                double longitude = Double.parseDouble(location.getString("longitude"));
                double latitude = Double.parseDouble(location.getString("latitude"));

                Log.d("Localisation", String.valueOf(longitude));
                Log.d("Localisation", String.valueOf(latitude));

                map.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).title("Job Here"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
