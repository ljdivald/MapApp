package com.example.ljudevit.mapapp;

import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        GoogleMap.OnMapClickListener mapClickListener = new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Geocoder gcd = new Geocoder(MapsActivity.this, Locale.getDefault());
                List<android.location.Address> addresses = null;

                try {
                    addresses = gcd.getFromLocation(latLng.latitude, latLng.longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String countryName = "";

                if (addresses.size() > 0)
                {
                    countryName=addresses.get(0).getCountryName();
                }

                Toast.makeText(MapsActivity.this,
                        countryName,
                        Toast.LENGTH_LONG).show();
            }
        };

        LatLng zagreb = new LatLng(48, 16);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.setOnMapClickListener(mapClickListener);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(zagreb));
    }

}
