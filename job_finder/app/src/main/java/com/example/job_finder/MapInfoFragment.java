package com.example.job_finder;

import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class MapInfoFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;
    private LinearLayout info_layout;
    private RecyclerView recyclerView;

    //Map du marker et de l'offre
    Map<Marker,Offer> markerOfferMap;

    Stack<Offer> selectedOffer = new Stack<>();

    MapInfoFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.map_info_fragment_layout, container, false);

        //info_layout = (LinearLayout) v.findViewById(R.id.info_layout);
        recyclerView = (RecyclerView) v.findViewById(R.id.offerRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(this);

        // Initialize variable
        markerOfferMap = new HashMap();


        return v;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        // Add a marker in paris and move the camera
        LatLng paris = new LatLng(48.864716, 2.349014);
        map.moveCamera(CameraUpdateFactory.newLatLng(paris));


        // Ajout des offres sur la carte
        for (Offer offer: OfferListSingleton.getOfferList()) {
            Marker m = map.addMarker(new MarkerOptions().position(new LatLng(offer.getLieu_travail_latitude(),offer.getLieu_travail_longitude())).title(offer.getIntitule()));
            markerOfferMap.put(m,offer);
        }

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.d("Map" ,marker.getTitle()+"clicked");
                //info_layout.setVisibility(View.VISIBLE);

                recyclerView.setVisibility(View.VISIBLE);

                if(!selectedOffer.isEmpty()){
                    selectedOffer.pop();
                }

                selectedOffer.push(markerOfferMap.get(marker));

                recyclerView.setAdapter(new OfferAdapter(selectedOffer));
                
                return false;
            }
        });

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //info_layout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
            }
        });

    }

}