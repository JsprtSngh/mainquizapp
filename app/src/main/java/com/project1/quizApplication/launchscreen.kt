package com.project1.quizApplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class launchscreen : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launchscreen)
        findViewById<Button>(R.id.start).setOnClickListener {
            val start = Intent(this, MainActivity::class.java)
            startActivity(start)
        }
    }
}