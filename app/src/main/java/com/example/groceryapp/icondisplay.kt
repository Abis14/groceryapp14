package com.example.groceryapp

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [icondisplay.newInstance] factory method to
 * create an instance of this fragment.
 */
class icondisplay : Fragment() {
    // TODO: Rename and change types of parameters

lateinit var setting:ImageView
lateinit var share:ImageView
lateinit var delete:ImageView
var title:String=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_icondisplay, container, false)
        setting=view.findViewById(R.id.setting)
        share=view.findViewById(R.id.astext)
        var bundle=arguments
        title=bundle?.getString("title").toString()
        delete=view.findViewById(R.id.delete)
        setting.setOnClickListener {
            val setting=Intent(activity,com.example.groceryapp.setting::class.java)
            startActivity(setting)

        }
        delete.setOnClickListener {
            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().uid.toString()).child("listbasicinfo").orderByChild("title").equalTo(title)
        }
        return view
    }


}