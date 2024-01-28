package com.travel_world.traveling.feature.home.fragments.homehotels;

import static com.travel_world.traveling.data.constants.Keys.KEY_HOTEL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mapbox.geojson.Point;
import com.mapbox.maps.AnnotatedFeature;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.ViewAnnotationOptions;
import com.travel_world.traveling.R;
import com.travel_world.traveling.databinding.HotelAnnotationViewBinding;
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
        if(hotel!=null) {
            mapView.getMapboxMap().setCamera(
                    new CameraOptions.Builder()
                            .center(Point.fromLngLat(hotel.getCoordinate().getLon(), hotel.getCoordinate().getLat()))
                            .pitch(0.0)
                            .zoom(20.0)
                            .bearing(0.0)
                            .build()
            );
        }
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addAnnotation(hotel);
    }

    private void addAnnotation(Result hotel) {
        HotelAnnotationViewBinding hotelView =  HotelAnnotationViewBinding.inflate(LayoutInflater.from(requireContext()),mapView,false);
        ViewAnnotationOptions.Builder viewAnnotationOptionsBuild = new ViewAnnotationOptions.Builder();
        viewAnnotationOptionsBuild.annotatedFeature(new AnnotatedFeature(Point.fromLngLat(hotel.getCoordinate().getLon(), hotel.getCoordinate().getLat())));
        viewAnnotationOptionsBuild.selected(true);
        viewAnnotationOptionsBuild.allowOverlap(true);
        viewAnnotationOptionsBuild.ignoreCameraPadding(true);
        mapView.getViewAnnotationManager().addViewAnnotation(hotelView.getRoot(),viewAnnotationOptionsBuild.build());
        hotelView.nameHotel.setText(hotel.getName());
    }
}