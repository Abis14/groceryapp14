package com.example.groceryapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

class gocerylistchildadapter(var dataset: ArrayList<listdetails>,var title:String):RecyclerView.Adapter<gocerylistchildadapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val check: CheckBox
        val text: TextView

        init {
            check = view.findViewById(R.id.itemcheck)
            text = view.findViewById(R.id.textView8)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grocerylistchildcard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var ids: String = ""

        holder.text.text = dataset[position].Itemdetails
        if(dataset[position].done=="true")
        {
            holder.check.isChecked=true
        }
        holder.check.setOnClickListener {
            if (holder.check.isChecked) {

                var ref = FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().uid.toString())

                ref.child("listbasicinfo").orderByChild("title").equalTo(title)
                    .get()
                    .addOnCompleteListener {
                        if (it.isSuccessful) {

                            it.result.children.forEach { children ->

                                ids = children.key.toString()


                            }
                        }
                    }
                var refs = FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().uid.toString()).child("listbasicinfo")
                    .child(ids).child("listdetails").orderByChild("category")
                    .equalTo(dataset[position].category).get().addOnCompleteListener {
                        it.result.children.forEach { children ->
                            val id = children.key.toString()
                            Log.d("ids", id)
                            FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().uid.toString())
                                .child("listbasicinfo").child(ids).child("listdetails").child(id).child("done").setValue("true")


                        }


                    }
            }

        }
    }
    fun databaseoperation()
    {


    }
        override fun getItemCount(): Int {
            return dataset.size
        }



}