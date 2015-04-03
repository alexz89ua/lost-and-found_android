package com.stfalcon.lostandfound;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.widget.ArrayAdapter;

import android.widget.Spinner;


import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Ifound extends ActionBarActivity implements   OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks {


    private SupportMapFragment mapFragment;

    private GoogleMap map;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private LatLng myPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ifound);



        mapFragment = SupportMapFragment.newInstance();
        mapFragment.getMapAsync(this);
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map, mapFragment);
        fragmentTransaction.commit();

        buildGoogleApiClient();
        mGoogleApiClient.connect();

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        Spinner elem = (Spinner) findViewById(R.id.spin);
        String[] drop_down_menu = getResources().getStringArray(R.array.dop_doun_menu);
        elem.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, drop_down_menu));

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        GoogleMap.OnMapClickListener click = new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                map.clear();
                myPosition = new LatLng(latLng.latitude, latLng.longitude);

                map.addMarker(new MarkerOptions()
                        .title("I Found here!")
                                // .snippet("")
                        .position(myPosition));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 17));
            }
        };

        GoogleMap.OnMapLongClickListener longclick = new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 8));
            }
        };

        map.setOnMapClickListener(click);
       map.setOnMapLongClickListener(longclick);

    }


    @Override
    public void onConnected(Bundle bundle) {

        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mCurrentLocation != null) {

            myPosition = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());

            map.addMarker(new MarkerOptions()
                    .title("I Found here!")
                            // .snippet("")
                    .position(myPosition));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 17));

        }

    }



   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ifound, menu);
        return true;
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onLocationChanged(Location location) {

    }
}

