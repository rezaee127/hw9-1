package com.example.netflix


import android.R.array
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


     /*   val edit: Editor = prefs.edit()
        edit.putInt("array_size", array.length)
        for (i in 0 until array.length) edit.putString("array_$i", array[i])
        edit.commit()


        val size: Int = prefs.getInt("array_size", 0)
        array = arrayOfNulls<String>(size)
        for (i in 0 until size) prefs.getString("array_$i", null)

      */
    }




}