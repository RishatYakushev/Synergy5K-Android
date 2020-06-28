package com.synergy.android.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.synergy.android.R
import com.synergy.android.map.MapFragment
import com.synergy.android.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import java.util.*


class MainActivity : AppCompatActivity(), KodeinAware, SwipeRefreshLayout.OnRefreshListener,
        MapFragment.OnMapListener {

    private var mLocationPermissionGranted = false
    private val mapFragment by lazy { MapFragment.newInstance() }
    private val profileFragment by lazy { ProfileFragment.newInstance() }

    override val kodein: Kodein by kodein()

    private var wheelFilter = false
    private var washFilter = false
    private var serviceFilter = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.map_menu -> {
                    loadFragment(mapFragment)
                    true
                }
                R.id.profile_menu -> {
                    loadFragment(profileFragment)
                    true
                }
                else -> false
            }
        }

        loadFragment(mapFragment)
    }

    private fun rqstPermission() {
        if (Build.VERSION.SDK_INT > 22) {
            val permissionsNeeded = ArrayList<String>()
            for (permission in PERMISSIONS_FOR_REQUEST) {
                if (ContextCompat.checkSelfPermission(this, permission) !=
                        PackageManager.PERMISSION_GRANTED
                ) {
                    permissionsNeeded.add(permission)
                } else if (permission == Manifest.permission.ACCESS_FINE_LOCATION) {
                    mLocationPermissionGranted = true
                }
            }
            if (permissionsNeeded.size > 0) {
                requestPermissions(
                        permissionsNeeded.toTypedArray(),
                        PERMISSIONS_REQUEST_ALL
                )
            } else {
                showCurrentLocation()
            }
        } else {
            mLocationPermissionGranted = true
            showCurrentLocation()
        }
    }

    override fun onMapReady() {
        showCurrentLocation()
    }

    private fun showCurrentLocation() {
        if (mLocationPermissionGranted) {
            mapFragment.currentLocation()
        } else {
            rqstPermission()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fl_content, fragment)
        ft.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_ALL) {
            mLocationPermissionGranted = true
            showCurrentLocation()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.wheel_action -> {
                wheelFilter = !wheelFilter
                item.isChecked = wheelFilter
            }
            R.id.wash_action -> {
                washFilter = !washFilter
                item.isChecked = washFilter
            }
            R.id.service_action -> {
                serviceFilter = !serviceFilter
                item.isChecked = serviceFilter
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRefresh() {
    }

    private fun visibleProgress(show: Boolean) {
//        refreshLayout.isRefreshing = show
    }

    private fun showProfile() {
        cl_main.visibility = View.VISIBLE
    }

    override fun onError(errorMessage: String) {
        showError(errorMessage)
    }

    private fun showError(errorText: String?) {
        errorText?.let {
            Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        internal var PERMISSIONS_FOR_REQUEST = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION
        )
        const val PERMISSIONS_REQUEST_ALL = 0x7c4
    }
}
