package com.travel_world.traveling.feature.home.fragments.homehotels

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.PointF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.core.graphics.scale
import androidx.fragment.app.Fragment
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.extension.style.layers.addLayer
import com.mapbox.maps.extension.style.layers.generated.LineLayer
import com.mapbox.maps.extension.style.layers.properties.generated.IconAnchor
import com.mapbox.maps.plugin.annotation.AnnotationConfig
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.viewannotation.ViewAnnotationManager
import com.mapbox.maps.viewannotation.annotatedLayerFeature
import com.mapbox.maps.viewannotation.geometry
import com.mapbox.maps.viewannotation.viewAnnotationOptions
import com.travel_world.traveling.R
import com.travel_world.traveling.data.constants.Keys.KEY_HOTEL
import com.travel_world.traveling.databinding.FragmentMapHotelBinding
import com.travel_world.traveling.domain.hotels.Result
import javax.annotation.Resource

class MapHotelFragment : Fragment() {
    private lateinit var viewAnnotationManager: ViewAnnotationManager
    private lateinit var binding : FragmentMapHotelBinding
    private lateinit var hotel : Result


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapHotelBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        if(arguments != null) {
            createViewMapBox()
            createLayer()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            addPoint(Point.fromLngLat(hotel.coordinate.lon, hotel.coordinate.lat),R.id.name_hotel,hotel.name);
        }
    }
    private fun createViewMapBox() {
        hotel = requireArguments().getSerializable(KEY_HOTEL) as Result
        binding.mapView.mapboxMap.setCamera(
            CameraOptions.Builder()
                .center(
                    Point.fromLngLat(
                        hotel.coordinate.lon,
                        hotel.coordinate.lat
                    )
                )
                .pitch(0.0)
                .zoom(20.0)
                .bearing(0.0)
                .build()
        )
        viewAnnotationManager = binding.mapView.viewAnnotationManager
    }

    private fun createLayer()
    {
        var lineLayer = LineLayer(LAYER_ID, SOURCE_ID)
        // Add the line layer to the mapView
        binding.mapView.mapboxMap.addLayer(lineLayer)
    }
    private fun addPoint(point: Point, @IdRes textViewId : Int, text: String) {
// add point annotation
        var bitmap = BitmapFactory.decodeResource(requireContext().resources,
            R.drawable.marcador_pos
        )
        bitmap = bitmap.scale(200,200,true)
        val annotationPlugin = binding.mapView.annotations
        val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
            .withPoint(point)
            .withIconImage(bitmap)
            .withIconAnchor(IconAnchor.BOTTOM)
        val pointAnnotationManager = annotationPlugin.createPointAnnotationManager(AnnotationConfig(layerId = LAYER_ID))
        val pointAnnotation = pointAnnotationManager.create(pointAnnotationOptions)
        val viewAnnotationManager = binding.mapView.viewAnnotationManager
// add view annotation attached to the point
        val viewAnnotation = viewAnnotationManager.addViewAnnotation(
            resId = R.layout.item_hotel_annotation_view,
            options = viewAnnotationOptions {
                annotatedLayerFeature(LAYER_ID) {
                    featureId(pointAnnotation.id)
                }
            }
        )
        viewAnnotation.findViewById<TextView>(textViewId).text = text
    }
    private companion object {
        const val SOURCE_ID = "source_id"
        const val LAYER_ID = "layer_id"
    }
}