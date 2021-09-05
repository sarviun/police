package com.nuivras.police

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.nuivras.police.databinding.FragmentMapBinding



private val BOUND_CORNER_NW = LatLng(59.82092226851996, -10.90326118629127)
private val BOUND_CORNER_SE = LatLng(50.37120029901955, 2.4121683786592567)
private val RESTRICTED_BOUNDS_AREA = LatLngBounds.Builder()
    .include(BOUND_CORNER_NW)
    .include(BOUND_CORNER_SE)
    .build()


class MapFragment : Fragment() {

    lateinit var mMapboxMap: MapboxMap

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //MapBox init
        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_access_token))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val view = binding.root

        //mapbox
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync { mapboxMap ->
            mMapboxMap = mapboxMap

            //setting style
            mapboxMap.setStyle(Style.MAPBOX_STREETS) {
                // Map is set up and the style has loaded. Now you can add data or make other map adjustments
                mapboxMap.setLatLngBoundsForCameraTarget(RESTRICTED_BOUNDS_AREA);
                mapboxMap.animateCamera(CameraUpdateFactory.newLatLngBounds(RESTRICTED_BOUNDS_AREA, 50), 500)
                mapboxMap.setMinZoomPreference(4.3)
            }

            mapboxMap.addOnMapClickListener { point ->
                Toast.makeText(context, String.format("User clicked at: %s", point.toString()), Toast.LENGTH_LONG).show()
                true
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}