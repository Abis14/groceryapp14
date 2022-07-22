package com.example.groceryapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList
import java.util.HashMap

class grocerylistparentadapter(var dataset: HashMap<String, ArrayList<listdetails>>,var listbasicinfo: listbasicinfo): RecyclerView.Adapter<grocerylistparentadapter.ViewHolder>() {
    class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
val title:TextView
val childrecy:RecyclerView
init {
    title=view.findViewById(R.id.textView6)
    childrecy=view.findViewById(R.id.grocerylistchildrec)
}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.grocerylistparentcard,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        var childlist: java.util.ArrayList<listdetails> = java.util.ArrayList()
//        Log.d("mylist", dataset[position].listdetails.toString())
//        dataset[position].listdetails?.forEach {
//            childlist.add(it.value)

        var key:MutableList<String>
        key=dataset.keys.toMutableList()

        Log.d("jjjj",key.toString())
        var categorylis: java.util.ArrayList<String> = java.util.ArrayList()

if(key[position].toString()=="Done")
{
    listbasicinfo.listdetails?.forEach {
        if(it.value.done=="true") {
            dataset.get("Done")?.add(it.value)
            holder.title.text = key[position].toString()
            var childadapter: gocerylistchildadapter =
                gocerylistchildadapter(dataset.getValue(key[position]), listbasicinfo.title!!)
            holder.childrecy.adapter = childadapter
            holder.childrecy.layoutManager =
                LinearLayoutManager(holder.childrecy.context, LinearLayoutManager.VERTICAL, false)
        }

    }
}
        else {
    holder.title.text = key[position].toString()
    var childadapter: gocerylistchildadapter =
        gocerylistchildadapter(dataset.getValue(key[position]), listbasicinfo.title!!)
    holder.childrecy.adapter = childadapter
    holder.childrecy.layoutManager =
        LinearLayoutManager(holder.childrecy.context, LinearLayoutManager.VERTICAL, false)
}

        }


    override fun getItemCount(): Int {
   return dataset.size
    }
}