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


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
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
        // Add a marker in Sydney and move the camera
        LatLng zagreb = new LatLng(48, 16);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.setOnMapClickListener(mapClickListener);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(zagreb));
    }


  /*  @Override
   public void onMapClick(LatLng latLng) throws IOException {
        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<android.location.Address> addresses = gcd.getFromLocation(latLng.latitude, latLng.longitude, 1);

        String countryName = "";

        if (addresses.size() > 0)
        {
            countryName=addresses.get(0).getCountryName();
        }

        Toast.makeText(MapsActivity.this,
                countryName,
                Toast.LENGTH_LONG).show();
    }*/
}
