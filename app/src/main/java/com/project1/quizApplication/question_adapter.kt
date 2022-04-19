package com.project1.quizApplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class question_adapter(var list : MutableSet<questions>) : RecyclerView.Adapter<question_adapter.Viewholder>() {

    class Viewholder(itemview : View): RecyclerView.ViewHolder(itemview) {
        val question = itemview.findViewById<TextView>(R.id.question)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(LayoutInflater.from(parent.context).inflate(R.layout.question_viewpager,parent,false))
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.question.setText(list.elementAt(position).question)
        Log.e("viewpager",list.elementAt(position).question)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
