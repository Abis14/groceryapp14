package com.example.groceryapp

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [menu.newInstance] factory method to
 * create an instance of this fragment.
 */
class menu : Fragment() {
  lateinit var rec:RecyclerView
  lateinit var data:ArrayList<String>
lateinit var img:ArrayList<Any>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_menu, container, false)
        rec=view.findViewById(R.id.menurec)
        data= ArrayList()
        data.add("RateAPP")
        data.add("ShareApp")
        data.add("Logout")
        img=ArrayList()
        img.add(R.drawable.rates)
        img.add(R.drawable.share)
        img.add(R.drawable.logout)

        rec.layoutManager=LinearLayoutManager(activity)
        var adapter:menuadapter=menuadapter(data,img)
        rec.adapter=adapter
        return view    }



}