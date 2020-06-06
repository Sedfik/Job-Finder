package com.example.job_finder;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    MapView mapView;
    GoogleMap map;

    MapFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map_layout, container, false);


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(this);


        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        // Add a marker in paris and move the camera
        LatLng paris = new LatLng(48.864716, 2.349014);
        map.moveCamera(CameraUpdateFactory.newLatLng(paris));

        String data = JSONutils.loadJSONFromAsset("offres_emploi", getContext());
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

                MarkerOptions marker = new MarkerOptions();

                marker.position(new LatLng(latitude,longitude)).title("Job Here");

                map.addMarker(marker);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}