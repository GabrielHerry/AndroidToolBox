package com.example.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_life_cycle.*

class LifeCycle : AppCompatActivity() {
//class LifeCycle : AppCompatActivity(), OnFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            var a = 1
            setContentView(R.layout.activity_life_cycle)
            changeFrag.setOnClickListener {
                var newFragment: Fragment?
                var WFragment = WhiteFragment()
                if (a == 1) {
                    newFragment = FirstFragment()

                    a = 2;
                } else {

                    newFragment = SecondFragment()
                    a = 1;
                }
                supportFragmentManager.beginTransaction().replace(R.id.fragment, newFragment).commit()


            }
        }

    override fun onStart() {
        super.onStart()
        // make a toast
       // Toast.makeText(this, "Hi  we are in OnStart() function !", Toast.LENGTH_LONG).show()
        Log.i(TAG, "onStart")
        textlifecycle.text =  "Hi  we are in Onstart() function !"

    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
        textlifecycle.text =  "Hi  we are in OnResume() function !"
    }

    val TAG = "LifeCycle_StateChange"

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
        Toast.makeText(this, "The LifeCycle activity is destroy (Mouahahha) !", Toast.LENGTH_LONG).show()

    }

}
