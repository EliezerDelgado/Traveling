package com.travel_world.traveling.feature.home.fragments.homehotels;

import static com.travel_world.traveling.data.constants.Keys.KEY_HOTEL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.travel_world.traveling.R;
import com.travel_world.traveling.domain.hotels.Result;

public class MapHotelFragment extends Fragment {

    private MapView mapView;
    private Result hotel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map_hotel, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.mapView);
        if (getArguments() != null) {
            hotel= (Result) (getArguments().getSerializable(KEY_HOTEL));
        }
        if(hotel!=null)
        mapView.getMapboxMap().setCamera(
                new CameraOptions.Builder()
                        .center(Point.fromLngLat(hotel.getCoordinate().getLon(),hotel.getCoordinate().getLat()))
                        .pitch(0.0)
                        .zoom(2.0)
                        .bearing(0.0)
                        .build()
        );
        return v;
    }
}