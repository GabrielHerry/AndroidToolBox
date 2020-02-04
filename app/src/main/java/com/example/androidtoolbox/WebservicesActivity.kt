
package com.example.androidtoolbox

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
import kotlinx.android.synthetic.main.recycler_view_contact_cell.*

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

         /*          var strResp = response.toString()
                  val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("results")
                var jsonInner: JSONObject = jsonArray.getJSONObject(0)
                textwebservices.text = jsonInner.get("gender").toString()
                   // textwebservices.text= result.results[0].gender
                   */

                var allResults: AllResults = Gson().fromJson(response, AllResults::class.java)

                contactRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                contactRecyclerView.adapter = AllResultAdapter(allResults.results)

            },
                Response.ErrorListener { textwebservices!!.text = "That didn't work!" })
        queue.add(stringReq)


    }




}


class AllResults (
    var results: Array<User>
)


class User (
    var gender: String? ,
    var name: Name?,
    var email: String?,
    var picture: Picture?
)

class Name (
    var title: String?,
    var first: String?,
    var last: String?
)

class Picture (
    var large: String?
)


/*
    val contactList = ArrayList<ContactModel>()
    val contacts = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
 //   val contacts
    while(contacts?.moveToNext() ?: false) {
     //   val displayName = contacts?.getString(contacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
        val contactModel = ContactModel()
        contactModel.displayName = displayName.toString()
        contactList.add(contactModel)
    }
    contactRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    contactRecyclerView.adapter = ContactsAdapter(contactList)
}

*/


