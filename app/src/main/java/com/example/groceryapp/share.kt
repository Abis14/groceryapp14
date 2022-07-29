package com.example.groceryapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.ktx.Firebase

class share : AppCompatActivity() {
    var id: String = ""
    lateinit var text:TextView
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        val title=intent.getStringExtra("title")
        text = findViewById(R.id.textView16)
        FirebaseDatabase.getInstance().getReference("grocerylist").child("listbasicinfo")
            .orderByChild("title").equalTo(title).get().addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result.children.forEach { children ->
                        id = children.key.toString()
                    }

                }


            }
        button = findViewById(R.id.button7)
        button.setOnClickListener {
            val myuri = createshareuri(id)
Log.d("query",myuri.toString())
            val d = dynamiclink(myuri)
            val mydata = "hi please bring these grocery ".toString()
            text.text = d.toString()

            val action = Intent()
            action.action = Intent.ACTION_SEND
            action.putExtra(Intent.EXTRA_SUBJECT, mydata)
            action.putExtra(Intent.EXTRA_TEXT, d.toString())
            action.type = "text/plain"

            startActivity(action)

        }
    }
    fun createshareuri(id: String): Uri {
        val builder = Uri.Builder()
        builder.scheme("http")
        builder.authority("groceryapp")
            .appendPath("grocerylist")
            .appendQueryParameter("id", id)
        return builder.build()
    }

    fun dynamiclink(myuri: Uri): Uri {
        val dynamiclink = FirebaseDynamicLinks.getInstance().createDynamicLink().setLink(myuri)
            .setDomainUriPrefix("https://abis.page.link/")
            .setAndroidParameters(DynamicLink.AndroidParameters.Builder().build())
            .buildDynamicLink()
        return dynamiclink.uri
    }

}