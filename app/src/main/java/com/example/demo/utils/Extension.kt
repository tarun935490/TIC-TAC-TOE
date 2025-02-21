package com.example.demo.utils

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.demo.R

fun Activity.showToastLong(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
}
fun Activity.goToActivity(destinationActivity: Class<*>){
    startActivity(Intent(this,destinationActivity))

}



fun Activity.goToActivity3(destinationActivity: Class<*>){
    startActivity(Intent(this,destinationActivity))
    finish()

}

    fun Fragment.goToFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

fun Activity.showSoftKeyBoard(view: View) {
    val inputMethodManager =
        this.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, 0)
}
fun isValidIndianPhoneNumber(phoneNumber: String): Boolean {
    val regex = Regex("^[6-9][0-9]{9}\$")
    return regex.matches(phoneNumber)
}

fun Activity.showToastShort(msg:String){
Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}
//Listview,recyclerview
//viewPager,Tablayout
//Firebase realtime database: Event listeners
