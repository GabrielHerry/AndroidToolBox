package com.example.androidtoolbox

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidtoolbox.Constants
import kotlinx.android.synthetic.main.activity_formulaire.*
import org.json.JSONObject
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.androidtoolbox.Constants.Companion.contactPermissionRequestCode
import java.text.SimpleDateFormat


class FormulaireActivity : AppCompatActivity() {

    val JsonForm = JSONObject()

    var currentDate = Date()
    var userAge = 0

    fun saveFormulaire(){
        JsonForm.put(Constants.FormName, collectName.text.toString())
        JsonForm.put(Constants.FormfirstName, collectFirstName.text.toString())
        JsonForm.put(Constants.FormBirthday, collectBirthday.text.toString())
        // clean textForm
        collectName.text = null
        collectFirstName.text = null
        collectBirthday.text = null



    }

    fun createJsonFile () {
        // convert in a string
        var stringForJson :String
        stringForJson = JsonForm.toString()

        var file = File(cacheDir.absolutePath.toString()+"/JsonForm.txt")

        file.writeText(stringForJson)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulaire)

        collectBirthday.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus) {
                collectBirthday.clearFocus()
                val dialog = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        onDateChoose(year, month, dayOfMonth)
                        userAge = getAge(year, month, dayOfMonth)
                    },
                    1990,
                    7,
                    25)
                dialog.show()
            }
        }

        buttonSaveForm.setOnClickListener {
            saveFormulaire()
            createJsonFile()
        }


        // Set a click listener for button widget
        SeeForm.setOnClickListener{
            // Initialize a new instance of
            val builder = AlertDialog.Builder(this)

            // Set the alert dialog title
            builder.setTitle("Information du Json : ")

            // Display a message on alert dialog
            var file = File(cacheDir.absolutePath.toString()+"/JsonForm.txt")
            val fileRead = file.readText()
            val jsonFormRead = JSONObject(fileRead)

            builder.setMessage(getString(R.string.messageAlertDialogName)+jsonFormRead.getString(Constants.FormName)+
                    getString(R.string.messageAlertDialogFirstName)+jsonFormRead.getString(Constants.FormfirstName)+
                    getString(R.string.messageAlertDialogBirthday)+jsonFormRead.getString(Constants.FormBirthday)+
                    "\nVous avez : "+userAge.toString())
            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }




    }

    fun onDateChoose(year: Int, month: Int, day: Int) {
        collectBirthday.setText(String.format("%02d/%02d/%04d", day, month+1, year))
    }

    fun getAge(year: Int, month: Int, day: Int): Int {

        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val dateString = formatter.format(currentDate)
        val components = dateString.split("/")

        var age = components[2].toInt() - year
        if (components[1].toInt() < month) {
            age--
        }
        else if (components[1].toInt() == month &&
            components[0].toInt() < day) {
            age --
        }
            return age
    }

}
