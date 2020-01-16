package com.puldroid.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.addAuthStateListener {
            if(it.currentUser != null){
                startActivity(Intent(this,ChatActivity::class.java))
            }
        }





        getUser("Pulkit",20,false)
        getUser(isActive = false,name = "Pulkit",age = 20)
        button.setOnClickListener {

            firebaseAuth.createUserWithEmailAndPassword(emailInp.text.toString(),passInp.text.toString())
                .addOnSuccessListener {
                    val user = it.user
                    startActivity(Intent(this,ChatActivity::class.java))
                }
        }

    }

    fun getUser(name: String, age: Int, isActive: Boolean){
        ("Pulkit").getVowel()

    }

}
fun String.getVowel():String{
    var a = ""
    for(i in this){
        if(i == 'a' || i == 'e' || i == 'o' || i == 'u'|| i == 'i'){
            a += a
        }
    }
    return a
}
