package com.example.groceryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class search : AppCompatActivity() {
   lateinit var rec:RecyclerView
   lateinit var text:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search2)
        rec=findViewById(R.id.searchrecycler)
        text=findViewById(R.id.searchText)

    }
}

class infoViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {


}
