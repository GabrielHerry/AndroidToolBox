package com.example.androidtoolbox

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.content.SharedPreferences
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        validatebutton.setOnClickListener {
            doLogin()
        }
        checkuser()
    }



    //saving data
    fun saveData() {

        val sharedPref = this.getSharedPreferences(Constants.SharedPrefName, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(Constants.SPId, identifiantfild.text.toString())
            putString(Constants.SPMdp, passwordfild.text.toString())
            commit()
        }
    }

    fun checkuser() {

        val sharedPref = this.getSharedPreferences(Constants.SharedPrefName,Context.MODE_PRIVATE) ?: return
        val str_identifiant = sharedPref.getString(Constants.SPId, "") ?:""
        val str_motdepasse = sharedPref.getString(Constants.SPMdp, "") ?:""

        if(str_identifiant == Constants.goodIdentifier && str_motdepasse == Constants.goodPassword )
        {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }


    }




    fun doLogin() {
        if (canLog(identifiantfild.text.toString(), passwordfild.text.toString())) {
            saveData()
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()

        }
    }

    fun canLog(identifier: String, password: String): Boolean {
        return identifier == Constants.goodIdentifier && password == Constants.goodPassword
    }

}


