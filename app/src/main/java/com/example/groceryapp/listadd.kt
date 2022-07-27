package com.example.groceryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.*
import androidx.appcompat.widget.Toolbar

class listadd : AppCompatActivity() {
   lateinit var addbtn:Button
    lateinit var framelayouts: FrameLayout
     var titles:String=""
    lateinit var mfragment:addtolist
    //lateinit var empty:frame
    lateinit var titletext:TextView
    lateinit var shareimage:ImageView
    lateinit var icon:ImageView
    lateinit var toolbar:Toolbar
    lateinit var icondisplay: icondisplay
    private val fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
//        var customtitle=requestWindowFeature(
//            Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.activity_listadd)
        super.onCreate(savedInstanceState)
icondisplay= icondisplay()
toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
supportActionBar?.title=""
        titletext=findViewById(R.id.textView11)
        shareimage=findViewById(R.id.imageView6)
        shareimage.setOnClickListener {

        }
        icon=findViewById(R.id.imageView12)
        icon.setOnClickListener {
            val fragmentTransaction=fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.icondisplay,icondisplay)
            val bundle=Bundle()
            bundle.putString("title",titles)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

        }
        addbtn=findViewById(R.id.plus)
        framelayouts=findViewById(R.id.details)
        loademptyfragment()

//       if(customtitle)
//       {
//           window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.listactionbar)
//       }
//        var title:TextView=findViewById(R.id.textView5)
        titles= intent.getStringExtra("data").toString()
titletext.text=titles

        addbtn.setOnClickListener{
            loadfragment()
        }


mfragment=addtolist()

    }

    private fun loademptyfragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.details, frame())
        val bundle=Bundle()
        bundle.putString("title",titles)

        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun loadfragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val bundle=Bundle()
        bundle.putString("title",titles)
         mfragment.arguments=bundle

        fragmentTransaction.replace(R.id.details, mfragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}