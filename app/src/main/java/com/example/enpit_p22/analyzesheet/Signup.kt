package com.example.enpit_p22.analyzesheet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.content_signup.*
import org.jetbrains.anko.startActivity

class Signup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setSupportActionBar(toolbar)

        registerButtom.setOnClickListener{

            performRegister()
        }
        already_textview.setOnClickListener{

            startActivity<LoginActivity>()
        }
    }

    private fun performRegister(){
        val email = emailEdit_signup.text.toString()
        val password = passwordEdit_signup.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "please enter text in email or password", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("RegisterActivity", "email is " + email)
        Log.d("RegisterActivity", "password: $password")

        //Firebase Authentication to create a user with email and password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                //else if cuccsessful
                Log.d("Main", "Successfully created user with uid: ${it.result.user.uid}")
            }
            .addOnFailureListener {
                Log.d("Main", "Failed to create user: ${it.message}")
                Toast.makeText(this, "Failed to create user:", Toast.LENGTH_SHORT).show()
            }
    }

}
