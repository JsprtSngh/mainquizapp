package com.project1.quizApplication

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.FirebaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.concurrent.TimeUnit

data class  questions(val question: String , val a: String, val b : String, val c: String, val d: String, val correctanswer: String){

}

class MainActivity : AppCompatActivity() {
    val TAG = "Checking"
    lateinit var name :String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val one = questions("Richest person of the world", "Elon musk","Mukesh Ambani","Jack Ma","Bill Gates","Elon musk")
        val two = questions("Richest person of india","Elon musk","Mukesh Ambani","Jack Ma","Bill Gates","Mukesh Ambani")
        val three = questions("Richest person of china","Elon musk","Mukesh Ambani","Jack Ma","Bill Gates","Jack Ma")
        var questions = mutableSetOf<questions>(one, two, three)
        //timer
        var tim = 30;
        val timer = findViewById<TextView>(R.id.timer)
        val duration = TimeUnit.MINUTES.toMillis(1)
        object: CountDownTimer(tim*1000.toLong(), 1000){
            override fun onTick(p0: Long) {
                tim--
                timer.setText(tim.toString());
            }
            override fun onFinish() {
                //add your code here
            }
        }.start()

        var databaseReference = FirebaseDatabase.getInstance().getReference().child("Questions");
        val ref = FirebaseDatabase.getInstance().getReference("Questions")
//        ref.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (messageSnapshot in dataSnapshot.children) {
//
//                    var q = questions(messageSnapshot.child("question").value.toString(),
//                        messageSnapshot.child("a").value.toString(),
//                        messageSnapshot.child("b").value.toString(),
//                        messageSnapshot.child("c").value.toString(),
//                        messageSnapshot.child("d").value.toString(),
//                        messageSnapshot.child("correctanswer").value.toString()
//                    )
//                    questions.add(q)
//
//                }
//                val viewpager = findViewById<ViewPager2>(R.id.viewpager)
//                var adapter = question_adapter(questions)
//                viewpager.adapter = adapter
//            }
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })


        //delete it , it is only for the offline test
        val viewpager = findViewById<ViewPager2>(R.id.viewpager)
        var adapter = question_adapter(questions, findViewById<Button>(R.id.next))
        viewpager.adapter = adapter
        viewpager.isUserInputEnabled = false
        viewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                findViewById<Button>(R.id.next).visibility = View.GONE
            }
        })
        findViewById<Button>(R.id.next).setOnClickListener {
            val current =viewpager.currentItem
            viewpager.currentItem = current+1
        }
    }


}


