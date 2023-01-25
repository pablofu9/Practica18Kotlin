package com.example.practica18_pablofuertes.activities

import android.content.Intent
import android.content.res.Configuration
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.practica18_pablofuertes.R

import com.example.practica18_pablofuertes.controller.SqliteHelper
import com.example.practica18_pablofuertes.fragments.Fragment_lista


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnVer:Button
    val fragment1 = Fragment_lista()
    val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.practica18_pablofuertes.R.layout.activity_main)
        btnVer=findViewById(com.example.practica18_pablofuertes.R.id.btnVer)
        btnVer.setOnClickListener(this)

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Landscape
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.frame1, fragment1)
            transaction.addToBackStack(null)
            transaction.commit()
        } else {
            // Portrait
        }

    }

    override fun onClick(v: View?) {
        var intent = Intent(this, Ver::class.java)
        startActivity(intent)
    }
}