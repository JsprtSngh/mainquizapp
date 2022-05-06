package com.project1.quizApplication

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class question_adapter(var list : MutableSet<questions>, val next : Button) : RecyclerView.Adapter<question_adapter.Viewholder>() {

    class Viewholder(itemview : View): RecyclerView.ViewHolder(itemview) {
        val question = itemview.findViewById<TextView>(R.id.question)
        val a = itemview.findViewById<TextView>(R.id.a)
        val b = itemview.findViewById<TextView>(R.id.b)
        val c = itemview.findViewById<TextView>(R.id.c)
        val d = itemview.findViewById<TextView>(R.id.d)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(LayoutInflater.from(parent.context).inflate(R.layout.question_viewpager,parent,false))
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.question.setText("Q"+(position+1)+": "+list.elementAt(position).question)
        holder.a.setText(list.elementAt(position).a)
        holder.b.setText(list.elementAt(position).b)
        holder.c.setText(list.elementAt(position).c)
        holder.d.setText(list.elementAt(position).d)

        holder.a.setOnClickListener {
            coloroption(holder.a, position)
            autocorrectoption(holder.a, holder.b,holder.c,holder.d, position)

        }
        holder.b.setOnClickListener {
            coloroption(holder.b, position)
            autocorrectoption(holder.a, holder.b,holder.c,holder.d, position)

        }
        holder.c.setOnClickListener {
            coloroption(holder.c, position)
            autocorrectoption(holder.a, holder.b,holder.c,holder.d, position)

        }
        holder.d.setOnClickListener {
            coloroption(holder.d, position)
            autocorrectoption(holder.a, holder.b,holder.c,holder.d, position)

        }
    }
    fun coloroption(a:TextView,position:Int){
        if(list.elementAt(position).correctanswer == a.text){
            a.setTextColor(Color.GREEN)

        }
        else{
            a.setTextColor(Color.RED)

        }
    }
    fun autocorrectoption(a:TextView, b:TextView,c:TextView ,d:TextView, position: Int){
        when(list.elementAt(position).correctanswer){
            a.text-> a.setTextColor(Color.GREEN)
            b.text-> b.setTextColor(Color.GREEN)
            c.text-> c.setTextColor(Color.GREEN)
            d.text-> d.setTextColor(Color.GREEN)
        }
        a.setOnClickListener(null)
        b.setOnClickListener(null)
        c.setOnClickListener(null)
        d.setOnClickListener(null)
        if (position != list.size-1){
            next.visibility  = View.VISIBLE
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }
}
