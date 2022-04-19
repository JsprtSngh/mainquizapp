package com.project1.quizApplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.FirebaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class  questions(val question: String , val a: String, val b : String, val c: String, val d: String, val correctanswer: String){

}

class MainActivity : AppCompatActivity() {
    val TAG = "Checking"
    lateinit var name :String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var questions = mutableSetOf<questions>()


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
                val viewpager = findViewById<ViewPager2>(R.id.viewpager)
                var adapter = question_adapter(questions)
                viewpager.adapter = adapter
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }


}