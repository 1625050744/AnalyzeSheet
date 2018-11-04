package com.example.enpit_p22.analyzesheet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_own_analysis.*
import kotlinx.android.synthetic.main.content_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        signInButtom.setOnClickListener {
            val email = emailEdit_login.text.toString()
            val password = passwordEdit_login.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{
                    if(!it.isSuccessful) {
                        Toast.makeText(this,"失敗",Toast.LENGTH_LONG)
                        return@addOnCompleteListener
                    }

                    //else if scusessful
                    Log.d("Main", "Successfully login user with uid: ${it.result.user.uid}")
                    startActivity<OwnAnalysisActivity>()
                }
                .addOnFailureListener{
                    Log.d("Main", "Failed to create user: ${it.message}")
                    Toast.makeText(this, "Faild to create user:", Toast.LENGTH_SHORT).show()
                }
        }

        back_to_register.setOnClickListener {
            startActivity<Signup>()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
