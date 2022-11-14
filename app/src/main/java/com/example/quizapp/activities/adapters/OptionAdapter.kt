package com.example.quizapp.activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.activities.models.Question

class OptionAdapter(val context: Context, val question: Question) :
    RecyclerView.Adapter<OptionAdapter.OptionViewholder>() {

    private var option: List<String> = listOf(question.option1, question.option2, question.option3, question.option4)

    inner class OptionViewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var optionView = itemView.findViewById<TextView>(R.id.quiz_option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item, parent, false)
        return OptionViewholder(view)
    }

    override fun onBindViewHolder(holder: OptionViewholder, position: Int) {
        holder.optionView.text = option[position]
        holder.itemView.setOnClickListener{
            Toast.makeText(context, option[position], Toast.LENGTH_LONG).show()
            question.userAnswer = option[position]
            notifyDataSetChanged()
        }
        if(question.userAnswer == option[position]){
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }
    }

    override fun getItemCount(): Int {
        return option.size
    }
}