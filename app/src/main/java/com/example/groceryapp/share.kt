package com.example.groceryapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.ktx.Firebase

class share : AppCompatActivity() {
    var id: String = ""
    lateinit var text:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
text=findViewById(R.id.textView16)
        FirebaseDatabase.getInstance().getReference("grocerylist").child("listbasicinfo")
            .orderByChild("title").equalTo("hhj").get().addOnCompleteListener {
            if (it.isSuccessful) {
                it.result.children.forEach { children ->
                    id = children.key.toString()
                }

            }


        }
        val myuri = createshareuri(id)
        val d=dynamiclink(myuri)
        val mydata="hi please bring these grocery $d"
        text.text=d.toString()
        val action=Intent()
        action.action=Intent.ACTION_SEND
        action.putExtra("listname",mydata)
        action.type="text/plain"
        startActivity(action)

    }

    fun createshareuri(id: String): Uri {
        val builder = Uri.Builder()
        builder.scheme("https")
        builder.authority("groceryapp")
            .appendPath("grocerylist")
            .appendQueryParameter("id", id)
        return builder.build()
    }

    fun dynamiclink(myuri: Uri): Uri {
        val dynamiclink = FirebaseDynamicLinks.getInstance().createDynamicLink().setLink(myuri)
            .setDomainUriPrefix("https://grocery.list.link")
            .setAndroidParameters(DynamicLink.AndroidParameters.Builder().build())
            .buildDynamicLink()
        return dynamiclink.uri
    }

}