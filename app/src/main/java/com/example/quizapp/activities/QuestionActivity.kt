package com.example.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.R
import com.example.quizapp.activities.adapters.OptionAdapter
import com.example.quizapp.activities.models.Question
import com.example.quizapp.activities.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    var quizzes: MutableList<Quiz>? = null
    var questions: MutableMap<String, Question>? = null
    var index = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        setUpFireStore()
        setUpEventListener()
    }

    private fun setUpEventListener() {
        btnPrevious.setOnClickListener{
            index--
            bindViews()
        }
        btnNext.setOnClickListener{
            index++
            bindViews()
        }
        btnSubmit.setOnClickListener{
            Log.d("FINALQUIZ", questions.toString())

            val intent = Intent(this, ResultActivity::class.java)
            val json: String = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)
        }
    }


    private fun setUpFireStore() {
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")

        if(date != null){
            firestore.collection("quizzes").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if(it != null && !it.isEmpty) {
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindViews()
                    }
                }
        }

    }

    private fun bindViews() {

        btnPrevious.visibility = View.GONE
        btnNext.visibility = View.GONE
        btnSubmit.visibility = View.GONE

        if(index == 1){
            btnNext.visibility = View.VISIBLE
        }else if(index == questions!!.size){
            btnPrevious.visibility = View.VISIBLE
            btnSubmit.visibility = View.VISIBLE
        }else{
            btnPrevious.visibility = View.VISIBLE
            btnNext.visibility = View.VISIBLE
        }
        val question = questions!!["question$index"]

        question?.let {
            description.text = it.description
            val optionAdapter = OptionAdapter(this, it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = optionAdapter
            optionList.setHasFixedSize(true)
        }

    }
}