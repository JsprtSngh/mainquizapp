package com.project1.quizApplication

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.mikhaellopez.circularprogressbar.CircularProgressBar
var currentuser ="null"
class result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val auth = Firebase.auth
        val intent = intent;
        val result = intent.getStringExtra("result")
        val noOfQuestions = intent.getIntExtra("questions",0)
        findViewById<TextView>(R.id.total).text = "/"+noOfQuestions.toString()
        findViewById<TextView>(R.id.got).text = result
        findViewById<TextView>(R.id.total_question).text = noOfQuestions.toString()
        findViewById<TextView>(R.id.correct_answer).text =result
        findViewById<TextView>(R.id.wronganswer).text = (noOfQuestions- (result?.toInt() ?: 0)).toString()
        findViewById<Button>(R.id.exit).setOnClickListener {
            onBackPressed()
        }

        val databaseReference = FirebaseDatabase.getInstance().getReference("Result")
        val s = currentuser.replace('@',' ')
        val c = s.replace('.',' ')
        databaseReference.child(c).push().setValue(result)
        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        circularProgressBar.apply {
            if(result!= null){
                setProgressWithAnimation(result.toFloat(), 2000) // =1s
            }


            // Set Progress Max
            progressMax = noOfQuestions.toFloat()

            // Set ProgressBar Color
            progressBarColor = resources.getColor(R.color.correctoption)
            // or with gradient
//            progressBarColorStart = Color.GRAY
//            progressBarColorEnd = Color.RED
//            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            // Set background ProgressBar Color
            backgroundProgressBarColor = resources.getColor(R.color.wrongoption)
            // or with gradient
//            backgroundProgressBarColorStart = Color.WHITE
//            backgroundProgressBarColorEnd = Color.RED
//            backgroundProgressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            // Set Width
            progressBarWidth = 10f // in DP
            backgroundProgressBarWidth = 9f // in DP

            // Other
            roundBorder = true
            startAngle = 180f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
        }
    }

    override fun onBackPressed() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            .setTitle("Exit")
            .setMessage("Do you want exit?")
        builder.setPositiveButton("Yes",{ d: DialogInterface, i:Int->
            super.onBackPressed()
        })
        builder.setNegativeButton("No"){ d: DialogInterface, i:Int->

        }
        builder.show()
    }
}