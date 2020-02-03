
package com.example.androidtoolbox

import org.junit.Test
import org.junit.Assert.*
import java.text.SimpleDateFormat

class FormulaireAgeTest {

    fun setup(): FormulaireActivity {
        val sut = FormulaireActivity()
        val formatter = SimpleDateFormat ( "dd/MM/yyyy")
        sut.currentDate = formatter.parse ("29/01/2020")
        return sut
    }

    @Test
    fun simpleYear_test() {
        val sut = FormulaireActivity()
        val age = sut.getAge(2000,1,1)
        assertEquals(20,age)
    }

    @Test
    fun simpleMonth_test() {
        val sut = setup()
        val age = sut.getAge(2000,2,1)
        assertEquals(19,age)
    }

    @Test
    fun simpleDay_test() {
        val sut = setup()
        val age = sut.getAge(2000,1,30)
        assertEquals(19,age)
    }
}