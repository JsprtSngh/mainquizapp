package com.project1.quizApplication

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class launchscreen : AppCompatActivity(){
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launchscreen)
        val progressbar = findViewById<LottieAnimationView>(R.id.progressBar)
        findViewById<Button>(R.id.start).setOnClickListener {
            progressbar.visibility = View.VISIBLE
            progressbar.playAnimation()

            var email = findViewById<EditText>(R.id.email).text.toString().trim()
            var password = findViewById<EditText>(R.id.password).text.toString().trim()
            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                findViewById<EditText>(R.id.email).setError("Enter email")
                findViewById<EditText>(R.id.password).setError("Enter password")
                progressbar.visibility = View.GONE
            }
            else if(TextUtils.isEmpty(email)){
                findViewById<EditText>(R.id.email).setError("Enter email")
                progressbar.visibility = View.GONE

            }
            else if(TextUtils.isEmpty(password)){
                findViewById<EditText>(R.id.password).setError("Enter password")
                progressbar.visibility = View.GONE

            }
            else{

                auth = Firebase.auth
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's informatio
                        val user = auth.currentUser
                        val start = Intent(this, MainActivity::class.java)
                        startActivity(start)
                        currentuser = findViewById<EditText>(R.id.email).text.toString()
                        finish()

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("wrong", "createUserWithEmail:failure", task.exception)
                        val snack = Snackbar.make(findViewById(R.id.main),"Wrong credentials",Snackbar.LENGTH_LONG)
                        snack.show()
                        progressbar.visibility = View.GONE
                    }


                }

            }



        }
    }
}