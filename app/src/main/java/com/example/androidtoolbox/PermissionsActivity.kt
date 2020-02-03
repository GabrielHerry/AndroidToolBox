package com.example.androidtoolbox

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_permissions.*
import android.content.Intent
import android.provider.MediaStore
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.ContactsContract
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.androidtoolbox.Constants.Companion.contactPermissionRequestCode

import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import android.widget.TextView
import com.google.android.gms.location.*



class PermissionsActivity : AppCompatActivity() {


    // var for localisation
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    val PERMISSION_POSITION_ID = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        buttonAccessGallery.setOnClickListener {
            pickFromGallery()
        }

        requestPermission(android.Manifest.permission.READ_CONTACTS, contactPermissionRequestCode) { readContacts() }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()
    }

    // permission for webcam and pictures and contacts
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(grantResults.isNotEmpty()) {
            if (requestCode == Constants.contactPermissionRequestCode &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                readContacts()
            }
        }
        if (requestCode == PERMISSION_POSITION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Granted. Start getting the location information
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun requestPermission(permissionToRequest:String, requestCode: Int, handler:()->Unit) {
        if (ContextCompat.checkSelfPermission(this, permissionToRequest) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissionToRequest)) {
                // display toast
            }
            else
            {
            ActivityCompat.requestPermissions(this, arrayOf(permissionToRequest), requestCode)
            }
        }
        else
        {
            handler()
        }

    }

    fun readContacts() {
        var arrayOfListView  = ArrayList<String>()

        val adapter = ArrayAdapter(this, R.layout.listview_item, arrayOfListView)

        val listView: ListView = findViewById(R.id.listView1)
        listView.setAdapter(adapter)

        val contacts = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null)
        while(contacts?.moveToNext() ?: false) {
            val contact = contacts?.getString(contacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            Log.d("Contacts", contact)

            adapter?.add(contact)


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            when (requestCode) {
                Constants.GALLERY_REQUEST_CODE -> {

                    //give to the button the picture
                    if (data?.data != null) { // in gallery
                        buttonAccessGallery.setImageURI(data?.data)
                    } else { // in the camera
                        val bitmap = data?.extras?.get("data") as? Bitmap
                        bitmap?.let {
                            buttonAccessGallery.setImageBitmap(it)
                        }

                    }

                }
            }
    }

    fun pickFromGallery() {

        val galleryIntent = Intent()
        galleryIntent.type = "image/*"
        galleryIntent.action = Intent.ACTION_GET_CONTENT


        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) // Camera

        val intentChoose = Intent.createChooser(galleryIntent, "Gallery")
        intentChoose.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        startActivityForResult(intentChoose,Constants.GALLERY_REQUEST_CODE)
        // Toast.makeText(this, "my code" +GALLERY_REQUEST_CODE, Toast.LENGTH_SHORT).show()
    }

    // ----------------------------------------------------------
    // the code for position
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_POSITION_ID
        )
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        findViewById<TextView>(R.id.latTextView).text = location.latitude.toString()
                        findViewById<TextView>(R.id.lonTextView).text = location.longitude.toString()
                        Toast.makeText(this, "modification of location", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //mFusedLocationClient.requestLocationUpdates(
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            findViewById<TextView>(R.id.latTextView).text = mLastLocation.latitude.toString()
            findViewById<TextView>(R.id.lonTextView).text = mLastLocation.longitude.toString()

        }
    }
}