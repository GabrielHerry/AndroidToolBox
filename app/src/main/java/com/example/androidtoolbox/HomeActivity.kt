package com.example.androidtoolbox

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val intent = Intent(this, LifeCycle::class.java)

        buttoncycle.setOnClickListener {
            startActivity(intent)
    }

        val intent2 = Intent(this, LoginActivity::class.java)
        intent2.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

        val sharedPref = this.getSharedPreferences(Constants.SharedPrefName,Context.MODE_PRIVATE) ?: return
        val str_identifiant = sharedPref.getString(Constants.SPId, "")
        val str_motdepasse = sharedPref.getString(Constants.SPMdp, "")
        textSharedPref.text = "From SharedPref: $str_identifiant --- $str_motdepasse"


        disconnexionbutton.setOnClickListener {
            // clear the SharedPreferences
            sharedPref.edit().clear().apply()
            startActivity(intent2)
        }

        val intent3 = Intent(this, FormulaireActivity::class.java)

        buttonsave.setOnClickListener {
            startActivity(intent3)
        }

        val intent4 = Intent(this, PermissionsActivity::class.java)

        buttonpermissions.setOnClickListener {
            startActivity(intent4)
        }

        val intent5 = Intent(this, WebservicesActivity::class.java)

        buttonwebservice.setOnClickListener {
            startActivity(intent5)
        }




    }
}
