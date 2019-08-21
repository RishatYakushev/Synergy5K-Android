package com.synergy.android.map

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener {

    private var listener: OnMapListener? = null
    private lateinit var mGoogleApiClient: GoogleApiClient
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(com.synergy.android.R.layout.fragment_map, container, false)
        val mapFragment =
            childFragmentManager.findFragmentById(com.synergy.android.R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        return rootView
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mGoogleApiClient = GoogleApiClient.Builder(activity!!).apply {
            addConnectionCallbacks(this@MapFragment)
            addOnConnectionFailedListener(this@MapFragment)
            addApi(LocationServices.API)
        }.build()
        mGoogleApiClient.connect()
    }

    fun currentLocation() {
        try {
            LocationServices.getFusedLocationProviderClient(activity!!).apply {
                lastLocation?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        val mLastKnownLocation = it.result!!
                        moveMap(LatLng(mLastKnownLocation.latitude, mLastKnownLocation.longitude))
                    } else {
                        mMap.uiSettings.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e: SecurityException) {
            listener?.onError(e.message ?: "")
        }

        mMap.apply {
            isMyLocationEnabled = true
            uiSettings.isZoomControlsEnabled = true
        }
    }

    private fun moveMap(latLng: LatLng) {
        mMap.addMarker(MarkerOptions().position(latLng))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15F))
    }

    override fun onConnected(p0: Bundle?) {
        listener?.onMapReady()
    }

    override fun onConnectionSuspended(p0: Int) {
        mGoogleApiClient.connect()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        listener?.onError(p0.errorMessage ?: "")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMapListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnMapListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnMapListener {
        fun onMapReady()
        fun onError(errorMessage: String)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MapFragment()
    }
}
