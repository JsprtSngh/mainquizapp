package com.project1.quizApplication

import android.annotation.SuppressLint
import android.content.Context
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
import com.google.android.material.internal.ContextUtils.getActivity
import org.w3c.dom.Text

class question_adapter(val context : Context, var list : MutableSet<questions>, val next : Button, val submit :Button) : RecyclerView.Adapter<question_adapter.Viewholder>() {
    var result:Int = 0

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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.question.setText("Q"+(position+1)+": "+list.elementAt(position).question)
        holder.a.setText(list.elementAt(position).a)
        holder.b.setText(list.elementAt(position).b)
        holder.c.setText(list.elementAt(position).c)
        holder.d.setText(list.elementAt(position).d)

        setcolor(holder.a)
        setcolor(holder.b)
        setcolor(holder.c)
        setcolor(holder.d)
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
    fun setcolor(options:TextView){
        options.setTextColor( context.getResources().getColor(R.color.option))
        options.background = context.getDrawable(R.drawable.option_background)
    }
    @SuppressLint("RestrictedApi")
    fun coloroption(a:TextView, position:Int){
        if (position != list.size-1){
            next.visibility  = View.VISIBLE
        }
        else {
            submit.visibility = View.VISIBLE
        }
        if(list.elementAt(position).correctanswer == a.text){
            a.setTextColor( context.getResources().getColor(R.color.correctoption))
            a.background = context.getDrawable(R.drawable.correctoption)
            result ++
            Log.e("progress","done"+result.toString())

        }
        else{
            a.setTextColor(context.getResources().getColor(R.color.wrongoption))
            a.background = context.getDrawable(R.drawable.wrongoption)

        }


    }
    fun autocorrectoption(a:TextView, b:TextView,c:TextView ,d:TextView, position: Int){
        when(list.elementAt(position).correctanswer){
            a.text->{
                a.setTextColor(context.getResources().getColor(R.color.correctoption))
                a.background = context.getDrawable(R.drawable.correctoption)
            }
            b.text-> {
                b.setTextColor(context.getResources().getColor(R.color.correctoption))
                b.background = context.getDrawable(R.drawable.correctoption)
            }

            c.text-> {
                c.setTextColor(context.getResources().getColor(R.color.correctoption))
                c.background = context.getDrawable(R.drawable.correctoption)
            }

            d.text-> {
                d.setTextColor(context.getResources().getColor(R.color.correctoption))
                d.background = context.getDrawable(R.drawable.correctoption)
            }

        }
        a.setOnClickListener(null)
        b.setOnClickListener(null)
        c.setOnClickListener(null)
        d.setOnClickListener(null)
        if (position != list.size-1){
            next.visibility  = View.VISIBLE
        }
        else {
            submit.visibility = View.VISIBLE
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }
    fun getresult():Int{
        return result
    }
}
