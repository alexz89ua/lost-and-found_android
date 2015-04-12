package com.stfalcon.lostandfound.ui.fragments;


import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.stfalcon.lostandfound.R;

public class FoundFragment extends Fragment implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks {


    private SupportMapFragment mapFragment;

    private GoogleMap map;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation = null;
    private LatLng myPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_ifound, container, false);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        mapFragment = SupportMapFragment.newInstance();
        mapFragment.getMapAsync(this);

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map, mapFragment);
        fragmentTransaction.commit();

        buildGoogleApiClient();
        mGoogleApiClient.connect();

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        Spinner elem = (Spinner) view.findViewById(R.id.spin);
        String[] drop_down_menu = getResources().getStringArray(R.array.dop_doun_menu);
        elem.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, drop_down_menu));
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
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
    public void onConnectionSuspended(int i) {}

    @Override
    public void onLocationChanged(Location location) {

    }

}

