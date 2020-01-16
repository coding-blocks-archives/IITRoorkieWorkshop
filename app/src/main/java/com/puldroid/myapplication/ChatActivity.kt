package com.puldroid.myapplication

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.database.FirebaseListAdapter
import com.firebase.ui.database.FirebaseListOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        button2.setOnClickListener {
            FirebaseDatabase.getInstance().reference.child("messages")
                .push()
                .setValue(
                    ChatMessage(
                        messageInp.text.toString(),
                        System.currentTimeMillis(),
                        FirebaseAuth.getInstance().currentUser?.email.toString()
                    )
                )
        }

        val query = FirebaseDatabase.getInstance()
            .reference.child("messages")
        val options = FirebaseListOptions.Builder<ChatMessage>()
            .setLayout(android.R.layout.simple_expandable_list_item_1)
            .setQuery(query, ChatMessage::class.java)
            .build()
        val adapter = object : FirebaseListAdapter<ChatMessage>(options) {
            override fun populateView(v: View, model: ChatMessage, position: Int) {
                (v as TextView).apply {
                    text = model.message
                }
            }

        }
        adapter.startListening()

        messages.adapter = adapter
    }
}

data class ChatMessage(val message: String, val time: Long, val name: String) {
    constructor() : this("",0L,"")
}
