package com.example.androidtoolbox


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {

 //   var listener: OnFragmentListener? = null


    val TAG = "FirstFrag_StateChange"

    override fun onAttach(context: Context?) {
        super.onAttach(context)
     /*   if(context is OnFragmentListener) {
            listener = context
        }*/
        Log.i(TAG, "Hi onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,"Hi OnCreate")
    }

    override fun onCreateView ( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.i(TAG,"Hi OnCreateView")

        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_first, container, false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //you can set the title for your toolbar here for different fragments different titles
        activity!!.title = "Fragment num1"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(TAG,"Hi OnActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG,"Hi OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"Hi OnStart")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"Hi OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG,"Hi onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG,"Hi onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"Hi onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG,"Hi onDetach")
    }


}
/*
interface OnFragmentListener {
    fun changeFragment()
}*/
