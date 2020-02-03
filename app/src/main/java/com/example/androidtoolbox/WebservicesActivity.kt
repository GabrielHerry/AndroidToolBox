package com.example.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_webservices.*
import org.json.JSONArray
import org.json.JSONObject

class WebservicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webservices)
    }

    fun getOneContact () {

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://randomuser.me/api/"

        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("results")
                var str_user: String = ""
                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_user = str_user + "\n" + jsonInner.get("login")
                }
                textView!!.text = "response : $str_user "
            },
            Response.ErrorListener { textView!!.text = "That didn't work!" })
        queue.add(stringReq)
    }



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


}
