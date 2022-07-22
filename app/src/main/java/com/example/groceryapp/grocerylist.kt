package com.example.groceryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.google.gson.Gson

class grocerylist : AppCompatActivity() {
    lateinit var text:Button
    var lists:String? = null
    lateinit var assingedtoall:assingedtoalllist
    var fragmentManager=supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grocerylist)
        text=findViewById(R.id.button4)


val gson:Gson= Gson()
        val list= intent.getStringExtra("mydata")
        lists=list
        val listbasicinfo2:listbasicinfo=gson.fromJson(list,listbasicinfo::class.java)
        Log.d("lists",listbasicinfo2.toString())
        assingedtoall= assingedtoalllist()
        text.setOnClickListener {
            loadfragment()
        }

    }
    fun loadfragment()
    {
        var fragmenttransaction=fragmentManager.beginTransaction()
        val bundle:Bundle= Bundle()
        bundle.putString("mydata",lists)
        assingedtoall.arguments=bundle
        fragmenttransaction.replace(R.id.fr2,assingedtoall)
        fragmenttransaction.commit()
    }
}