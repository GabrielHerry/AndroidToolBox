
package com.example.androidtoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_webservices.*
import org.json.JSONArray
import org.json.JSONObject
import kotlinx.android.synthetic.main.recycler_view_user_cell.*

class WebservicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webservices)
        getContacts()
    }

    fun getContacts() {

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://randomuser.me/api/?results=20"



        // Request a string response from the provided URL.
        val stringReq =
            StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->


                var allResults: AllResults = Gson().fromJson(response, AllResults::class.java)

                contactRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                contactRecyclerView.adapter = AllResultAdapter(allResults.results) {
                    val intent = Intent(this, ShowUserActivity::class.java)

                    intent.putExtra("genderToShow", it.gender)

                    intent.putExtra("nameTitleToShow", it.name?.title)
                    intent.putExtra("nameFirstToShow", it.name?.first)
                    intent.putExtra("nameLastToShow", it.name?.last)

                    intent.putExtra("locationStreetIntToShow", it.location?.street?.number)
                    intent.putExtra("locationStreetNameToShow", it.location?.street?.name)

                    intent.putExtra("locationCityToShow", it.location?.city)
                    intent.putExtra("locationStateToShow", it.location?.state)
                    intent.putExtra("locationContryToShow", it.location?.contry)
                    intent.putExtra("locationPostcodeToShow", it.location?.postcode)

                    intent.putExtra("locationCoordinatesLatitudeToShow", it.location?.coordinates?.latitude)
                    intent.putExtra("locationCoordinatesLongitudeToShow", it.location?.coordinates?.longitude)

                    intent.putExtra("mailToShow", it.email)

                    intent.putExtra("pictureToShow", it.picture?.large)

                    startActivity(intent)
                }

            },
                Response.ErrorListener { textwebservices!!.text = "That didn't work!" })
        queue.add(stringReq)


    }




}