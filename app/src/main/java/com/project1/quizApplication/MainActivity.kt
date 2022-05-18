package com.project1.quizApplication

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.Lottie
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
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
        val progressbar  = findViewById<com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar>(R.id.progressbar)
//        val one = questions("Richest person of the world", "Elon musk","Mukesh Ambani","Jack Ma","Bill Gates","Elon musk")
//        val two = questions("Richest person of india","Elon musk","Mukesh Ambani","Jack Ma","Bill Gates","Mukesh Ambani")
//        val four =questions("Richest","Elon musk","Mukesh Ambani","Jack Ma","Bill Gates","Mukesh Ambani")
//        val three = questions("Richest person of china","Elon musk","Mukesh Ambani","Jack Ma","Bill Gates","Jack Ma")
//        val five = questions(" person of india","Elon musk","Mukesh Ambani","Jack Ma","Bill Gates","Mukesh Ambani")
//        val six = questions("of india","Elon musk","Mukesh Ambani","Jack Ma","Bill Gates","Mukesh Ambani")
//        val seven = questions("Richest ab","Elon musk","Mukesh Ambani","Jack Ma","Bill Gates","Mukesh Ambani")
//        val eight = questions("Richest  cd person of india","Elon musk","Mukesh Ambani","Jack Ma","Bill Gates","Mukesh Ambani")
//        val nine = questions("Richest ddddd person of india","Elon musk","Mukesh Ambani","Jack Ma","Bill Gates","Mukesh Ambani")

        var questions = mutableSetOf<questions>()
        //timer
        var tim = 30;
        var viewpager = findViewById<ViewPager2>(R.id.viewpager)
        var adapter :question_adapter = question_adapter(this@MainActivity,questions, findViewById<Button>(R.id.next), findViewById(R.id.submit))
        val loading2 = findViewById<LottieAnimationView>(R.id.loading2)
        loading2.visibility = View.VISIBLE
        loading2.playAnimation()
        loading2.repeatCount = LottieDrawable.INFINITE
        var databaseReference = FirebaseDatabase.getInstance().getReference().child("Questions");
        val ref = FirebaseDatabase.getInstance().getReference("Questions")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (messageSnapshot in dataSnapshot.children) {

                    var q = questions(messageSnapshot.child("question").value.toString(),
                        messageSnapshot.child("a").value.toString(),
                        messageSnapshot.child("b").value.toString(),
                        messageSnapshot.child("c").value.toString(),
                        messageSnapshot.child("d").value.toString(),
                        messageSnapshot.child("correctanswer").value.toString()
                    )
                    questions.add(q)

                }

                adapter = question_adapter(this@MainActivity,questions, findViewById<Button>(R.id.next), findViewById(R.id.submit))
                progressbar.max = questions.size.toFloat()
                viewpager.adapter = adapter
                loading2.visibility = View.GONE
                viewpager.isUserInputEnabled = false
                viewpager.offscreenPageLimit =1
                viewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                        Log.e("progress",adapter.getresult().toString())
                        progressbar.setProgress(position+1)
                        findViewById<Button>(R.id.next).visibility = View.GONE

                    }

                })
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        //delete it , it is only for the offline test
//        val viewpager = findViewById<ViewPager2>(R.id.viewpager)
//        var adapter = question_adapter(this,questions, findViewById<Button>(R.id.next), findViewById(R.id.submit))
//        viewpager.adapter = adapter
//        viewpager.isUserInputEnabled = false
//        viewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//                Log.e("progress",adapter.getresult().toString())
//                progressbar.setProgress(position+1)
//                findViewById<Button>(R.id.next).visibility = View.GONE
//
//            }
//        })
        findViewById<Button>(R.id.next).setOnClickListener {
            val current =viewpager.currentItem
            viewpager.currentItem = current+1
        }
        findViewById<Button>(R.id.submit).setOnClickListener {
            var i = Intent(this, result::class.java)
            i.putExtra("result", adapter.getresult().toString())
            i.putExtra("questions", questions.size)
            startActivity(i)
            finish()
        }
    }


}


