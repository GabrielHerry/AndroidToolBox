package com.example.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_show_user.*
import kotlinx.android.synthetic.main.recycler_view_user_cell.view.*

class ShowUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_user)

        textgender.text = intent.getStringExtra("genderToShow")
        textFirst.text = intent.getStringExtra("nameFirstToShow")
        textLast.text = intent.getStringExtra("nameLastToShow")

        textInt.text = intent.getStringExtra("locationStreetIntToShow")
        textNameStreet.text = intent.getStringExtra("locationStreetNameToShow")

        textCity.text = intent.getStringExtra("locationCityToShow")
        textState.text = intent.getStringExtra("locationStateToShow")
        textCountry.text = intent.getStringExtra("locationContryToShow")
        textPostcode.text = intent.getStringExtra("locationPostcodeToShow")

        textLatitude.text = intent.getStringExtra("locationCoordinatesLatitudeToShow")
        textLongitude.text = intent.getStringExtra("locationCoordinatesLongitudeToShow")

        textMail.text = intent.getStringExtra("mailToShow")

        Picasso
            .with(baseContext)
            .load(intent.getStringExtra("pictureToShow"))
            .into(imagePicture)

    }

}
